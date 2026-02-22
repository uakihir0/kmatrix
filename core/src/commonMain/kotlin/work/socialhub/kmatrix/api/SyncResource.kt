package work.socialhub.kmatrix.api

import work.socialhub.kmatrix.api.request.sync.SyncRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.sync.SyncResponse
import kotlin.js.JsExport

@JsExport
interface SyncResource {

    /**
     * GET /_matrix/client/v3/sync
     * Synchronise the client's state with the latest state on the server.
     */
    suspend fun sync(request: SyncRequest): Response<SyncResponse>

    @JsExport.Ignore
    fun syncBlocking(request: SyncRequest): Response<SyncResponse>
}
