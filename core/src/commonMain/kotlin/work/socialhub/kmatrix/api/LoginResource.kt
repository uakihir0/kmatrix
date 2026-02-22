package work.socialhub.kmatrix.api

import work.socialhub.kmatrix.api.request.login.LoginPasswordRequest
import work.socialhub.kmatrix.api.request.login.LoginSsoRedirectRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.login.LoginGetLoginFlowsResponse
import work.socialhub.kmatrix.api.response.login.LoginPasswordResponse
import kotlin.js.JsExport

@JsExport
interface LoginResource {

    /**
     * Gets the homeserver's supported login types.
     * GET /_matrix/client/v3/login
     * (No authentication required)
     */
    suspend fun getLoginFlows(): Response<LoginGetLoginFlowsResponse>

    @JsExport.Ignore
    fun getLoginFlowsBlocking(): Response<LoginGetLoginFlowsResponse>

    /**
     * Authenticates the user with password and returns an access token.
     * POST /_matrix/client/v3/login
     * (No authentication required)
     */
    suspend fun loginWithPassword(
        request: LoginPasswordRequest
    ): Response<LoginPasswordResponse>

    @JsExport.Ignore
    fun loginWithPasswordBlocking(
        request: LoginPasswordRequest
    ): Response<LoginPasswordResponse>

    /**
     * Returns the SSO redirect URL for browser-based authentication.
     * GET /_matrix/client/v3/login/sso/redirect?redirectUrl={redirectUrl}
     * (No authentication required)
     */
    fun ssoRedirectUrl(
        request: LoginSsoRedirectRequest
    ): String
}
