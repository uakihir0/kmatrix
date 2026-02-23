package work.socialhub.kmatrix.internal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import work.socialhub.khttpclient.HttpRequest
import work.socialhub.kmatrix.api.UserDirectoryResource
import work.socialhub.kmatrix.api.request.userdirectory.UserDirectorySearchRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.userdirectory.UserDirectorySearchResponse
import work.socialhub.kmatrix.internal.InternalUtility.toJson
import work.socialhub.kmatrix.util.Headers.AUTHORIZATION
import work.socialhub.kmatrix.util.MediaType
import work.socialhub.kmatrix.util.toBlocking

class UserDirectoryResourceImpl(
    uri: String,
    accessToken: String,
) : AbstractAuthResourceImpl(uri, accessToken),
    UserDirectoryResource {

    override suspend fun search(
        request: UserDirectorySearchRequest
    ): Response<UserDirectorySearchResponse> {
        return proceed {
            val body = toJson(
                SearchBody(
                    searchTerm = request.searchTerm ?: "",
                    limit = request.limit,
                )
            )
            HttpRequest()
                .url("${uri}/_matrix/client/v3/user_directory/search")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .json(body)
                .post()
        }
    }

    override fun searchBlocking(
        request: UserDirectorySearchRequest
    ): Response<UserDirectorySearchResponse> {
        return toBlocking { search(request) }
    }

    @Serializable
    private data class SearchBody(
        @SerialName("search_term")
        val searchTerm: String,
        @SerialName("limit")
        val limit: Int? = null,
    )
}
