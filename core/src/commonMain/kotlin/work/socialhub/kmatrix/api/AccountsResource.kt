package work.socialhub.kmatrix.api

import work.socialhub.kmatrix.api.request.account.ChangePasswordRequest
import work.socialhub.kmatrix.api.request.account.RegisterRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.ResponseUnit
import work.socialhub.kmatrix.api.response.account.RegisterResponse
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

    /**
     * Invalidates the current access token.
     * POST /_matrix/client/v3/logout
     */
    suspend fun logout(): ResponseUnit

    @JsExport.Ignore
    fun logoutBlocking(): ResponseUnit

    /**
     * Invalidates all access tokens for the user.
     * POST /_matrix/client/v3/logout/all
     */
    suspend fun logoutAll(): ResponseUnit

    @JsExport.Ignore
    fun logoutAllBlocking(): ResponseUnit

    /**
     * Creates a new account on the server.
     * POST /_matrix/client/v3/register
     * (No authentication required)
     */
    suspend fun register(
        request: RegisterRequest
    ): Response<RegisterResponse>

    @JsExport.Ignore
    fun registerBlocking(
        request: RegisterRequest
    ): Response<RegisterResponse>

    /**
     * Changes the password for the current account.
     * POST /_matrix/client/v3/account/password
     */
    suspend fun changePassword(
        request: ChangePasswordRequest
    ): ResponseUnit

    @JsExport.Ignore
    fun changePasswordBlocking(
        request: ChangePasswordRequest
    ): ResponseUnit
}

