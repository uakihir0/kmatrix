package work.socialhub.kmatrix.internal

import work.socialhub.khttpclient.HttpRequest
import work.socialhub.kmatrix.api.VersionsResource
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.versions.VersionsGetResponse
import work.socialhub.kmatrix.util.MediaType
import work.socialhub.kmatrix.util.toBlocking

class VersionsResourceImpl(
    uri: String,
) : AbstractResourceImpl(uri),
    VersionsResource {

    override suspend fun getVersions(): Response<VersionsGetResponse> {
        return proceed {
            HttpRequest()
                .url("${uri}/_matrix/client/versions")
                .accept(MediaType.JSON)
                .get()
        }
    }

    override fun getVersionsBlocking(): Response<VersionsGetResponse> {
        return toBlocking { getVersions() }
    }
}
