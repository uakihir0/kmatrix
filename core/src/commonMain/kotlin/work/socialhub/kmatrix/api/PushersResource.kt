package work.socialhub.kmatrix.api

import work.socialhub.kmatrix.api.request.pushers.PushersSetRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.ResponseUnit
import work.socialhub.kmatrix.api.response.pushers.PushersGetResponse
import kotlin.js.JsExport

@JsExport
interface PushersResource {

    /**
     * GET /_matrix/client/v3/pushers
     * Gets all currently active pushers for the authenticated user.
     */
    suspend fun getPushers(): Response<PushersGetResponse>

    @JsExport.Ignore
    fun getPushersBlocking(): Response<PushersGetResponse>

    /**
     * POST /_matrix/client/v3/pushers/set
     * This endpoint allows the creation, modification and deletion of pushers
     * for this user ID. The behaviour of this endpoint varies depending on the
     * values in the JSON body.
     */
    suspend fun setPusher(request: PushersSetRequest): ResponseUnit

    @JsExport.Ignore
    fun setPusherBlocking(request: PushersSetRequest): ResponseUnit
}
