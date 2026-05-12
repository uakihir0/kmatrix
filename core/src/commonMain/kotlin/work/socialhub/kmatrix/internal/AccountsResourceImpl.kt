package work.socialhub.kmatrix.internal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import work.socialhub.khttpclient.HttpRequest
import work.socialhub.kmatrix.api.AccountsResource
import work.socialhub.kmatrix.api.request.account.ChangePasswordRequest
import work.socialhub.kmatrix.api.request.account.RegisterRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.ResponseUnit
import work.socialhub.kmatrix.api.response.account.RegisterResponse
import work.socialhub.kmatrix.api.response.accounts.AccountsWhoamiResponse
import work.socialhub.kmatrix.internal.InternalUtility.toJson
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

    override suspend fun register(
        request: RegisterRequest
    ): Response<RegisterResponse> {
        return proceed {
            val body = toJson(RegisterBody(
                username = request.username,
                password = request.password,
                initialDeviceDisplayName = request.initialDeviceDisplayName,
                auth = request.auth,
                session = request.session,
                did = request.did,
                bindEmail = request.bindEmail,
            ))
            HttpRequest()
                .url("${uri}/_matrix/client/v3/register")
                .accept(MediaType.JSON)
                .json(body)
                .post()
        }
    }

    override fun registerBlocking(
        request: RegisterRequest
    ): Response<RegisterResponse> {
        return toBlocking { register(request) }
    }

    override suspend fun changePassword(
        request: ChangePasswordRequest
    ): ResponseUnit {
        return proceedUnit {
            val body = toJson(ChangePasswordBody(
                auth = ChangePasswordAuth(
                    type = "m.login.password",
                    password = request.auth ?: "",
                ),
                newPassword = request.newPassword,
                logoutDevices = request.logoutDevices,
                logoutDevicesAll = request.logoutDevicesAll,
            ))
            HttpRequest()
                .url("${uri}/_matrix/client/v3/account/password")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .json(body)
                .post()
        }
    }

    override fun changePasswordBlocking(
        request: ChangePasswordRequest
    ): ResponseUnit {
        return toBlocking { changePassword(request) }
    }

    @Serializable
    private data class RegisterBody(
        @SerialName("username")
        val username: String? = null,
        @SerialName("password")
        val password: String? = null,
        @SerialName("initial_device_display_name")
        val initialDeviceDisplayName: String? = null,
        @SerialName("auth")
        val auth: String? = null,
        @SerialName("session")
        val session: String? = null,
        @SerialName("did")
        val did: String? = null,
        @SerialName("bind_email")
        val bindEmail: Boolean? = null,
    )

    @Serializable
    private data class ChangePasswordBody(
        @SerialName("auth")
        val auth: ChangePasswordAuth,
        @SerialName("new_password")
        val newPassword: String?,
        @SerialName("logout_devices")
        val logoutDevices: Boolean? = null,
        @SerialName("logout_devices_all")
        val logoutDevicesAll: Boolean? = null,
    )

    @Serializable
    private data class ChangePasswordAuth(
        @SerialName("type")
        val type: String,
        @SerialName("password")
        val password: String,
    )
}
