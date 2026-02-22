package work.socialhub.kmatrix.internal

import work.socialhub.khttpclient.HttpRequest
import work.socialhub.kmatrix.api.AccountsResource
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.accounts.AccountsWhoamiResponse
import work.socialhub.kmatrix.util.Headers.AUTHORIZATION
import work.socialhub.kmatrix.util.MediaType
import work.socialhub.kmatrix.util.toBlocking

class AccountsResourceImpl(
    uri: String,
    accessToken: String,
) : AbstractAuthResourceImpl(uri, accessToken),
    AccountsResource {

    override suspend fun whoami(): Response<AccountsWhoamiResponse> {
        return proceed {
            HttpRequest()
                .url("${uri}/_matrix/client/v3/account/whoami")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .get()
        }
    }

    override fun whoamiBlocking(): Response<AccountsWhoamiResponse> {
        return toBlocking { whoami() }
    }
}
