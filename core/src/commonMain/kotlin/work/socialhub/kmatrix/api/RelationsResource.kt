package work.socialhub.kmatrix.api

import work.socialhub.kmatrix.api.request.relations.RelationsGetRequest
import work.socialhub.kmatrix.api.request.relations.ThreadsGetRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.relations.RelationsGetResponse
import work.socialhub.kmatrix.api.response.relations.ThreadsGetResponse
import kotlin.js.JsExport

@JsExport
interface RelationsResource {

    /**
     * GET /_matrix/client/v1/rooms/{roomId}/relations/{eventId}
     * Get events related to a given event (replies, reactions, edits, etc.)
     */
    suspend fun getRelations(
        request: RelationsGetRequest
    ): Response<RelationsGetResponse>

    @JsExport.Ignore
    fun getRelationsBlocking(
        request: RelationsGetRequest
    ): Response<RelationsGetResponse>

    /**
     * GET /_matrix/client/v1/rooms/{roomId}/threads
     * Get threads in a room.
     */
    suspend fun getThreads(
        request: ThreadsGetRequest
    ): Response<ThreadsGetResponse>

    @JsExport.Ignore
    fun getThreadsBlocking(
        request: ThreadsGetRequest
    ): Response<ThreadsGetResponse>
}
