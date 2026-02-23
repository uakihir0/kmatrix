package work.socialhub.kmatrix.api

import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.voip.VoIPGetTurnServerResponse
import kotlin.js.JsExport

@JsExport
interface VoIPResource {

    /**
     * GET /_matrix/client/v3/voip/turnServer
     * Get credentials for the client to use when initiating calls.
     */
    suspend fun getTurnServer(): Response<VoIPGetTurnServerResponse>

    @JsExport.Ignore
    fun getTurnServerBlocking(): Response<VoIPGetTurnServerResponse>
}
