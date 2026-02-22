package work.socialhub.kmatrix.internal

import work.socialhub.khttpclient.HttpRequest
import work.socialhub.khttpclient.HttpResponse
import work.socialhub.kmatrix.MatrixException
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.ResponseUnit
import work.socialhub.kmatrix.entity.share.RateLimit
import work.socialhub.kmatrix.internal.InternalUtility.fromJson

abstract class AbstractResourceImpl(
    val uri: String
) {

    protected suspend inline fun <reified T> proceed(
        function: suspend () -> HttpResponse
    ): Response<T> {
        try {
            val response = function()
            if (response.status == 200) {
                return Response(fromJson<T>(response.stringBody))
                    .also {
                        it.limit = RateLimit.of(response)
                        it.json = response.stringBody
                    }
            }
            throw MatrixException(
                response.status,
                response.stringBody
            )
        } catch (e: Exception) {
            throw e as? MatrixException
                ?: MatrixException(e)
        }
    }

    protected suspend inline fun proceedUnit(
        function: suspend () -> HttpResponse
    ): ResponseUnit {
        try {
            val response = function()
            if (response.status == 200) {
                return ResponseUnit().also {
                    it.limit = RateLimit.of(response)
                }
            }

            throw MatrixException(
                response.status,
                response.stringBody
            )
        } catch (e: Exception) {
            throw e as? MatrixException
                ?: MatrixException(e)
        }
    }

    fun HttpRequest.pwn(
        key: String,
        value: Any?,
    ): HttpRequest {
        if (value != null)
            param(key, value)
        return this
    }

    fun <T> HttpRequest.pwns(
        key: String,
        values: Array<T>?,
    ): HttpRequest {
        values?.forEach {
            param("${key}[]", it as Any)
        }
        return this
    }

    fun HttpRequest.qwn(
        key: String,
        value: Any?,
    ): HttpRequest {
        if (value != null)
            query(key, value)
        return this
    }

    fun <T> HttpRequest.qwns(
        key: String,
        values: Array<T>?,
    ): HttpRequest {
        values?.forEach {
            query("${key}[]", it as Any)
        }
        return this
    }
}
