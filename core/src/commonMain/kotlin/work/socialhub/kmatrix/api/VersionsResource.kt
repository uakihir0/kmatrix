package work.socialhub.kmatrix.api

import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.versions.VersionsGetResponse
import kotlin.js.JsExport

@JsExport
interface VersionsResource {

    /**
     * GET /_matrix/client/versions
     * Gets the versions of the specification supported by the server.
     */
    suspend fun getVersions(): Response<VersionsGetResponse>

    @JsExport.Ignore
    fun getVersionsBlocking(): Response<VersionsGetResponse>
}
