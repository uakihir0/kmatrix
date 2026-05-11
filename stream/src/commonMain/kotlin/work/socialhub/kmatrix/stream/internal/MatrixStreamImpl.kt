package work.socialhub.kmatrix.stream.internal

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import work.socialhub.kmatrix.api.request.sync.SyncRequest
import work.socialhub.kmatrix.api.response.sync.SyncResponse
import work.socialhub.kmatrix.stream.MatrixStream
import kotlin.js.JsExport

@JsExport
class MatrixStreamImpl(
    private val uri: String,
    private val accessToken: String,
) : MatrixStream {

    private val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }

    private val defaultConfig = object : MatrixStream.Config {
        override var timeout: Long = 30000L
        override var maxRetry: Int = 5
        override var initialBackoff: Long = 1000L
        override var maxBackoff: Long = 30000L
        override var enabled: Boolean = true
    }

    override fun uri() = uri
    override fun accessToken() = accessToken

    override fun syncStream(
        timeout: Long?,
        maxRetry: Int,
    ): Flow<SyncResponse> {
        val config = defaultConfig.copy()
        timeout?.let { config.timeout = it }
        maxRetry.let { config.maxRetry = it }
        return syncStreamInternal(null, config)
    }

    override fun syncStream(
        request: SyncRequest,
        maxRetry: Int,
    ): Flow<SyncResponse> {
        val config = defaultConfig.copy()
        maxRetry.let { config.maxRetry = it }
        return syncStreamInternal(request, config)
    }

    private fun syncStreamInternal(
        request: SyncRequest?,
        config: MatrixStream.Config,
    ): Flow<SyncResponse> = flow {
        var since: String? = null
        var retryCount = 0

        while (config.enabled) {
            try {
                val response = fetchSync(uri, accessToken, since, request, config.timeout)
                since = response.nextBatch
                retryCount = 0
                emit(response)
            } catch (e: Exception) {
                if (retryCount >= config.maxRetry) {
                    throw MatrixStreamException("Max retry count reached: $retryCount", e)
                }
                val backoff = minOf(
                    config.initialBackoff * (1L shl retryCount),
                    config.maxBackoff
                ) + (Math.random() * 1000).toLong()
                delay(backoff)
                retryCount++
            }
        }
    }

    private suspend fun fetchSync(
        uri: String,
        accessToken: String,
        since: String?,
        request: SyncRequest?,
        timeout: Long,
    ): SyncResponse {
        val queryParams = buildMap {
            if (since != null) put("since", since)
            if (timeout > 0) put("timeout", timeout.toString())
            request?.filter?.let { put("filter", it) }
            request?.fullState?.let { put("full_state", it.toString()) }
            request?.setPresence?.let { put("set_presence", it) }
        }

        val url = if (queryParams.isNotEmpty()) {
            val params = queryParams.map { "${it.key}=${it.value}" }.joinToString("&")
            "${uri}/_matrix/client/v3/sync?$params"
        } else {
            "${uri}/_matrix/client/v3/sync?timeout=$timeout"
        }

        var lastException: Throwable? = null
        repeat(3) { attempt ->
            try {
                val body = HttpClient {
                    install(HttpTimeout) {
                        requestTimeoutMillis = timeout + 5000L
                    }
                    install(ContentNegotiation) {
                        json(json)
                    }
                }.use { client ->
                    client.get(url) {
                        header(HttpHeaders.Authorization, "Bearer $accessToken")
                        header(HttpHeaders.Accept, ContentType.Application.Json.toString())
                    }.body<String>()
                }
                return json.decodeFromString<SyncResponse>(body)
            } catch (e: Exception) {
                lastException = e
                if (e is IOException) {
                    delay(1000L * (attempt + 1))
                } else {
                    throw e
                }
            }
        }
        throw lastException ?: IOException("Unknown error fetching sync")
    }

    private fun MatrixStream.Config.copy(): MatrixStream.Config {
        return object : MatrixStream.Config {
            override var timeout: Long = this@MatrixStreamImpl.defaultConfig.timeout
            override var maxRetry: Int = this@MatrixStreamImpl.defaultConfig.maxRetry
            override var initialBackoff: Long = this@MatrixStreamImpl.defaultConfig.initialBackoff
            override var maxBackoff: Long = this@MatrixStreamImpl.defaultConfig.maxBackoff
            override var enabled: Boolean = this@MatrixStreamImpl.defaultConfig.enabled
        }.apply {
            timeout = this@copy.timeout
            maxRetry = this@copy.maxRetry
            initialBackoff = this@copy.initialBackoff
            maxBackoff = this@copy.maxBackoff
            enabled = this@copy.enabled
        }
    }
}

class MatrixStreamException(
    message: String,
    cause: Throwable? = null,
) : RuntimeException(message, cause)

