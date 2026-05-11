package work.socialhub.kmatrix.stream

import kotlinx.coroutines.flow.Flow
import work.socialhub.kmatrix.api.request.sync.SyncRequest
import work.socialhub.kmatrix.api.response.sync.SyncResponse
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface MatrixStream {

    fun uri(): String
    fun accessToken(): String

    /**
     * Starts a streaming sync that continuously polls for updates.
     * Uses long-polling with exponential backoff on errors.
     */
    @JsName("syncStreamWithTimeout")
    fun syncStream(
        timeout: Long? = 30000L,
        maxRetry: Int = 5,
    ): Flow<SyncResponse>

    /**
     * Starts a streaming sync with a custom filter.
     */
    @JsName("syncStreamWithRequest")
    fun syncStream(
        request: SyncRequest,
        maxRetry: Int = 5,
    ): Flow<SyncResponse>

    /**
     * Configuration for the streaming sync behavior.
     */
    @JsExport.Ignore
    interface Config {
        var timeout: Long
        var maxRetry: Int
        var initialBackoff: Long
        var maxBackoff: Long
        var enabled: Boolean
    }
}
