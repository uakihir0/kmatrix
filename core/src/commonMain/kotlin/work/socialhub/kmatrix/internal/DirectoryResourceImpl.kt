package work.socialhub.kmatrix.internal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import work.socialhub.khttpclient.HttpRequest
import work.socialhub.kmatrix.api.DirectoryResource
import work.socialhub.kmatrix.api.request.directory.DirectoryGetPublicRoomsRequest
import work.socialhub.kmatrix.api.request.directory.DirectorySetAliasRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.ResponseUnit
import work.socialhub.kmatrix.api.response.directory.DirectoryGetPublicRoomsResponse
import work.socialhub.kmatrix.api.response.directory.DirectoryResolveAliasResponse
import work.socialhub.kmatrix.internal.InternalUtility.toJson
import work.socialhub.kmatrix.util.Headers.AUTHORIZATION
import work.socialhub.kmatrix.util.MediaType
import work.socialhub.kmatrix.util.toBlocking

class DirectoryResourceImpl(
    uri: String,
    accessToken: String,
) : AbstractAuthResourceImpl(uri, accessToken),
    DirectoryResource {

    override suspend fun resolveAlias(
        roomAlias: String
    ): Response<DirectoryResolveAliasResponse> {
        return proceed {
            HttpRequest()
                .url("${uri}/_matrix/client/v3/directory/room/${roomAlias}")
                .accept(MediaType.JSON)
                .get()
        }
    }

    override fun resolveAliasBlocking(
        roomAlias: String
    ): Response<DirectoryResolveAliasResponse> {
        return toBlocking { resolveAlias(roomAlias) }
    }

    override suspend fun setAlias(
        request: DirectorySetAliasRequest
    ): ResponseUnit {
        return proceedUnit {
            val roomAlias = request.roomAlias ?: ""
            val body = toJson(
                SetAliasBody(
                    roomId = request.roomId ?: "",
                )
            )
            HttpRequest()
                .url("${uri}/_matrix/client/v3/directory/room/${roomAlias}")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .json(body)
                .put()
        }
    }

    override fun setAliasBlocking(
        request: DirectorySetAliasRequest
    ): ResponseUnit {
        return toBlocking { setAlias(request) }
    }

    override suspend fun deleteAlias(
        roomAlias: String
    ): ResponseUnit {
        return proceedUnit {
            HttpRequest()
                .url("${uri}/_matrix/client/v3/directory/room/${roomAlias}")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .delete()
        }
    }

    override fun deleteAliasBlocking(
        roomAlias: String
    ): ResponseUnit {
        return toBlocking { deleteAlias(roomAlias) }
    }

    override suspend fun getPublicRooms(
        request: DirectoryGetPublicRoomsRequest
    ): Response<DirectoryGetPublicRoomsResponse> {
        return proceed {
            HttpRequest()
                .url("${uri}/_matrix/client/v3/publicRooms")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .qwn("limit", request.limit)
                .qwn("since", request.since)
                .qwn("server", request.server)
                .get()
        }
    }

    override fun getPublicRoomsBlocking(
        request: DirectoryGetPublicRoomsRequest
    ): Response<DirectoryGetPublicRoomsResponse> {
        return toBlocking { getPublicRooms(request) }
    }

    @Serializable
    private data class SetAliasBody(
        @SerialName("room_id")
        val roomId: String,
    )
}
