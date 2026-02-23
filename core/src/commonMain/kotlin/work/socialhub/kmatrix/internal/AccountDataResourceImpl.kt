package work.socialhub.kmatrix.internal

import work.socialhub.khttpclient.HttpRequest
import work.socialhub.kmatrix.MatrixException
import work.socialhub.kmatrix.api.AccountDataResource
import work.socialhub.kmatrix.api.request.accountdata.AccountDataGetRequest
import work.socialhub.kmatrix.api.request.accountdata.AccountDataSetRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.ResponseUnit
import work.socialhub.kmatrix.util.Headers.AUTHORIZATION
import work.socialhub.kmatrix.util.MediaType
import work.socialhub.kmatrix.util.toBlocking

class AccountDataResourceImpl(
    uri: String,
    accessToken: String,
) : AbstractAuthResourceImpl(uri, accessToken),
    AccountDataResource {

    override suspend fun getAccountData(
        request: AccountDataGetRequest
    ): Response<String> {
        try {
            val response = HttpRequest()
                .url("${uri}/_matrix/client/v3/user/${request.userId}/account_data/${request.type}")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .get()
            if (response.status == 200) {
                return Response(response.stringBody).also {
                    it.json = response.stringBody
                }
            }
            throw MatrixException(response.status, response.stringBody)
        } catch (e: Exception) {
            throw e as? MatrixException ?: MatrixException(e)
        }
    }

    override fun getAccountDataBlocking(
        request: AccountDataGetRequest
    ): Response<String> {
        return toBlocking { getAccountData(request) }
    }

    override suspend fun setAccountData(
        request: AccountDataSetRequest
    ): ResponseUnit {
        return proceedUnit {
            HttpRequest()
                .url("${uri}/_matrix/client/v3/user/${request.userId}/account_data/${request.type}")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .json(request.data ?: "{}")
                .put()
        }
    }

    override fun setAccountDataBlocking(
        request: AccountDataSetRequest
    ): ResponseUnit {
        return toBlocking { setAccountData(request) }
    }

    override suspend fun getRoomAccountData(
        request: AccountDataGetRequest
    ): Response<String> {
        try {
            val response = HttpRequest()
                .url("${uri}/_matrix/client/v3/user/${request.userId}/rooms/${request.roomId}/account_data/${request.type}")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .get()
            if (response.status == 200) {
                return Response(response.stringBody).also {
                    it.json = response.stringBody
                }
            }
            throw MatrixException(response.status, response.stringBody)
        } catch (e: Exception) {
            throw e as? MatrixException ?: MatrixException(e)
        }
    }

    override fun getRoomAccountDataBlocking(
        request: AccountDataGetRequest
    ): Response<String> {
        return toBlocking { getRoomAccountData(request) }
    }

    override suspend fun setRoomAccountData(
        request: AccountDataSetRequest
    ): ResponseUnit {
        return proceedUnit {
            HttpRequest()
                .url("${uri}/_matrix/client/v3/user/${request.userId}/rooms/${request.roomId}/account_data/${request.type}")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .json(request.data ?: "{}")
                .put()
        }
    }

    override fun setRoomAccountDataBlocking(
        request: AccountDataSetRequest
    ): ResponseUnit {
        return toBlocking { setRoomAccountData(request) }
    }
}
