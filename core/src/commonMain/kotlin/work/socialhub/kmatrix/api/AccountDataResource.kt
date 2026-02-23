package work.socialhub.kmatrix.api

import work.socialhub.kmatrix.api.request.accountdata.AccountDataGetRequest
import work.socialhub.kmatrix.api.request.accountdata.AccountDataSetRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.ResponseUnit
import kotlin.js.JsExport

@JsExport
interface AccountDataResource {

    /**
     * GET /_matrix/client/v3/user/{userId}/account_data/{type}
     * Get some account data for the user.
     */
    suspend fun getAccountData(
        request: AccountDataGetRequest
    ): Response<String>

    @JsExport.Ignore
    fun getAccountDataBlocking(
        request: AccountDataGetRequest
    ): Response<String>

    /**
     * PUT /_matrix/client/v3/user/{userId}/account_data/{type}
     * Set some account data for the user.
     */
    suspend fun setAccountData(
        request: AccountDataSetRequest
    ): ResponseUnit

    @JsExport.Ignore
    fun setAccountDataBlocking(
        request: AccountDataSetRequest
    ): ResponseUnit

    /**
     * GET /_matrix/client/v3/user/{userId}/rooms/{roomId}/account_data/{type}
     * Get some account data for the user in a room.
     */
    suspend fun getRoomAccountData(
        request: AccountDataGetRequest
    ): Response<String>

    @JsExport.Ignore
    fun getRoomAccountDataBlocking(
        request: AccountDataGetRequest
    ): Response<String>

    /**
     * PUT /_matrix/client/v3/user/{userId}/rooms/{roomId}/account_data/{type}
     * Set some account data for the user in a room.
     */
    suspend fun setRoomAccountData(
        request: AccountDataSetRequest
    ): ResponseUnit

    @JsExport.Ignore
    fun setRoomAccountDataBlocking(
        request: AccountDataSetRequest
    ): ResponseUnit
}
