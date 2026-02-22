package work.socialhub.kmatrix.internal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import work.socialhub.khttpclient.HttpRequest
import work.socialhub.kmatrix.api.LoginResource
import work.socialhub.kmatrix.api.request.login.LoginPasswordRequest
import work.socialhub.kmatrix.api.request.login.LoginSsoRedirectRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.login.LoginGetLoginFlowsResponse
import work.socialhub.kmatrix.api.response.login.LoginPasswordResponse
import work.socialhub.kmatrix.internal.InternalUtility.toJson
import work.socialhub.kmatrix.util.MediaType
import work.socialhub.kmatrix.util.toBlocking

class LoginResourceImpl(
    uri: String,
) : AbstractResourceImpl(uri),
    LoginResource {

    override suspend fun getLoginFlows(): Response<LoginGetLoginFlowsResponse> {
        return proceed {
            HttpRequest()
                .url("${uri}/_matrix/client/v3/login")
                .accept(MediaType.JSON)
                .get()
        }
    }

    override fun getLoginFlowsBlocking(): Response<LoginGetLoginFlowsResponse> {
        return toBlocking { getLoginFlows() }
    }

    override suspend fun loginWithPassword(
        request: LoginPasswordRequest
    ): Response<LoginPasswordResponse> {
        return proceed {
            val body = toJson(LoginPasswordBody(
                identifier = LoginPasswordIdentifier(
                    user = request.user ?: "",
                ),
                password = request.password ?: "",
                initialDeviceDisplayName = request.initialDeviceDisplayName,
            ))
            HttpRequest()
                .url("${uri}/_matrix/client/v3/login")
                .accept(MediaType.JSON)
                .json(body)
                .post()
        }
    }

    override fun loginWithPasswordBlocking(
        request: LoginPasswordRequest
    ): Response<LoginPasswordResponse> {
        return toBlocking { loginWithPassword(request) }
    }

    override fun ssoRedirectUrl(
        request: LoginSsoRedirectRequest
    ): String {
        return "${uri}/_matrix/client/v3/login/sso/redirect?redirectUrl=${request.redirectUrl}"
    }

    @Serializable
    private data class LoginPasswordBody(
        @SerialName("type")
        val type: String = "m.login.password",
        @SerialName("identifier")
        val identifier: LoginPasswordIdentifier,
        @SerialName("password")
        val password: String,
        @SerialName("initial_device_display_name")
        val initialDeviceDisplayName: String? = null,
    )

    @Serializable
    private data class LoginPasswordIdentifier(
        @SerialName("type")
        val type: String = "m.id.user",
        @SerialName("user")
        val user: String,
    )
}
