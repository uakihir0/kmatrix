package work.socialhub.kmatrix.api

import work.socialhub.kmatrix.api.request.presence.PresenceSetRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.ResponseUnit
import work.socialhub.kmatrix.api.response.presence.PresenceGetResponse
import kotlin.js.JsExport

@JsExport
interface PresenceResource {

    /**
     * GET /_matrix/client/v3/presence/{userId}/status
     * Get the given user's presence state.
     */
    suspend fun getPresence(userId: String): Response<PresenceGetResponse>

    @JsExport.Ignore
    fun getPresenceBlocking(userId: String): Response<PresenceGetResponse>

    /**
     * PUT /_matrix/client/v3/presence/{userId}/status
     * Set the given user's presence state.
     */
    suspend fun setPresence(request: PresenceSetRequest): ResponseUnit

    @JsExport.Ignore
    fun setPresenceBlocking(request: PresenceSetRequest): ResponseUnit
}
