package work.socialhub.kmatrix.api

import work.socialhub.kmatrix.api.request.userdirectory.UserDirectorySearchRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.userdirectory.UserDirectorySearchResponse
import kotlin.js.JsExport

@JsExport
interface UserDirectoryResource {

    /**
     * POST /_matrix/client/v3/user_directory/search
     * Searches the user directory for users.
     */
    suspend fun search(
        request: UserDirectorySearchRequest
    ): Response<UserDirectorySearchResponse>

    @JsExport.Ignore
    fun searchBlocking(
        request: UserDirectorySearchRequest
    ): Response<UserDirectorySearchResponse>
}
