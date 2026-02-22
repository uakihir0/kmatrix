package work.socialhub.kmatrix.internal

import work.socialhub.khttpclient.HttpRequest
import work.socialhub.kmatrix.api.AccountsResource
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.ResponseUnit
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

    override suspend fun logout(): ResponseUnit {
        return proceedUnit {
            HttpRequest()
                .url("${uri}/_matrix/client/v3/logout")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .json("{}")
                .post()
        }
    }

    override fun logoutBlocking(): ResponseUnit {
        return toBlocking { logout() }
    }

    override suspend fun logoutAll(): ResponseUnit {
        return proceedUnit {
            HttpRequest()
                .url("${uri}/_matrix/client/v3/logout/all")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .json("{}")
                .post()
        }
    }

    override fun logoutAllBlocking(): ResponseUnit {
        return toBlocking { logoutAll() }
    }
}
