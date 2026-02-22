package work.socialhub.kmatrix.api

import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.accounts.AccountsWhoamiResponse
import kotlin.js.JsExport

@JsExport
interface AccountsResource {

    /**
     * Gets information about the owner of the access token.
     * GET /_matrix/client/v3/account/whoami
     */
    suspend fun whoami(): Response<AccountsWhoamiResponse>

    @JsExport.Ignore
    fun whoamiBlocking(): Response<AccountsWhoamiResponse>
}
