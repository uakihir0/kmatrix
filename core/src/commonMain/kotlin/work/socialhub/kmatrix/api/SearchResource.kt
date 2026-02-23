package work.socialhub.kmatrix.api

import work.socialhub.kmatrix.api.request.search.SearchRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.search.SearchResponse
import kotlin.js.JsExport

@JsExport
interface SearchResource {

    /**
     * POST /_matrix/client/v3/search
     * Perform a server-side search.
     */
    suspend fun search(request: SearchRequest): Response<SearchResponse>

    @JsExport.Ignore
    fun searchBlocking(request: SearchRequest): Response<SearchResponse>
}
