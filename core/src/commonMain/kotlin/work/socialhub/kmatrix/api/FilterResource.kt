package work.socialhub.kmatrix.api

import work.socialhub.kmatrix.api.request.filter.FilterCreateRequest
import work.socialhub.kmatrix.api.request.filter.FilterGetRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.filter.FilterCreateResponse
import work.socialhub.kmatrix.api.response.filter.FilterGetResponse
import kotlin.js.JsExport

@JsExport
interface FilterResource {

    /**
     * POST /_matrix/client/v3/user/{userId}/filter
     * Upload a new filter definition to the homeserver.
     */
    suspend fun createFilter(
        request: FilterCreateRequest
    ): Response<FilterCreateResponse>

    @JsExport.Ignore
    fun createFilterBlocking(
        request: FilterCreateRequest
    ): Response<FilterCreateResponse>

    /**
     * GET /_matrix/client/v3/user/{userId}/filter/{filterId}
     * Download a filter.
     */
    suspend fun getFilter(
        request: FilterGetRequest
    ): Response<FilterGetResponse>

    @JsExport.Ignore
    fun getFilterBlocking(
        request: FilterGetRequest
    ): Response<FilterGetResponse>
}
