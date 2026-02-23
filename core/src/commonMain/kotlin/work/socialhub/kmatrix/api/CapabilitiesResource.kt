package work.socialhub.kmatrix.api

import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.capabilities.CapabilitiesGetResponse
import kotlin.js.JsExport

@JsExport
interface CapabilitiesResource {

    /**
     * GET /_matrix/client/v3/capabilities
     * Gets information about the server's supported feature set
     * and other relevant capabilities.
     */
    suspend fun getCapabilities(): Response<CapabilitiesGetResponse>

    @JsExport.Ignore
    fun getCapabilitiesBlocking(): Response<CapabilitiesGetResponse>
}
