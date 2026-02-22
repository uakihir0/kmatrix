package work.socialhub.kmatrix.internal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import work.socialhub.khttpclient.HttpRequest
import work.socialhub.kmatrix.api.RoomsResource
import work.socialhub.kmatrix.api.request.rooms.RoomsCreateRoomRequest
import work.socialhub.kmatrix.api.request.rooms.RoomsInviteRequest
import work.socialhub.kmatrix.api.request.rooms.RoomsJoinRoomRequest
import work.socialhub.kmatrix.api.request.rooms.RoomsLeaveRoomRequest
import work.socialhub.kmatrix.api.request.rooms.RoomsSendMessageRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.ResponseUnit
import work.socialhub.kmatrix.api.response.rooms.RoomsCreateRoomResponse
import work.socialhub.kmatrix.api.response.rooms.RoomsGetJoinedRoomsResponse
import work.socialhub.kmatrix.api.response.rooms.RoomsGetRoomNameResponse
import work.socialhub.kmatrix.api.response.rooms.RoomsJoinRoomResponse
import work.socialhub.kmatrix.api.response.rooms.RoomsSendMessageResponse
import work.socialhub.kmatrix.internal.InternalUtility.toJson
import work.socialhub.kmatrix.util.Headers.AUTHORIZATION
import work.socialhub.kmatrix.util.MediaType
import work.socialhub.kmatrix.util.toBlocking
import kotlin.random.Random

class RoomsResourceImpl(
    uri: String,
    accessToken: String,
) : AbstractAuthResourceImpl(uri, accessToken),
    RoomsResource {

    private var txnCounter = 0L

    override suspend fun createRoom(
        request: RoomsCreateRoomRequest
    ): Response<RoomsCreateRoomResponse> {
        return proceed {
            val body = toJson(CreateRoomBody(
                visibility = request.visibility,
                roomAliasName = request.roomAliasName,
                name = request.name,
                topic = request.topic,
                invite = request.invite,
                preset = request.preset,
                isDirect = request.isDirect,
            ))
            HttpRequest()
                .url("${uri}/_matrix/client/v3/createRoom")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .json(body)
                .post()
        }
    }

    override fun createRoomBlocking(
        request: RoomsCreateRoomRequest
    ): Response<RoomsCreateRoomResponse> {
        return toBlocking { createRoom(request) }
    }

    override suspend fun joinRoom(
        request: RoomsJoinRoomRequest
    ): Response<RoomsJoinRoomResponse> {
        return proceed {
            val roomIdOrAlias = request.roomIdOrAlias ?: ""
            val body = toJson(JoinRoomBody(
                reason = request.reason,
            ))
            HttpRequest()
                .url("${uri}/_matrix/client/v3/join/${roomIdOrAlias}")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .json(body)
                .post()
        }
    }

    override fun joinRoomBlocking(
        request: RoomsJoinRoomRequest
    ): Response<RoomsJoinRoomResponse> {
        return toBlocking { joinRoom(request) }
    }

    override suspend fun leaveRoom(
        request: RoomsLeaveRoomRequest
    ): ResponseUnit {
        return proceedUnit {
            val roomId = request.roomId ?: ""
            val body = toJson(LeaveRoomBody(
                reason = request.reason,
            ))
            HttpRequest()
                .url("${uri}/_matrix/client/v3/rooms/${roomId}/leave")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .json(body)
                .post()
        }
    }

    override fun leaveRoomBlocking(
        request: RoomsLeaveRoomRequest
    ): ResponseUnit {
        return toBlocking { leaveRoom(request) }
    }

    override suspend fun invite(
        request: RoomsInviteRequest
    ): ResponseUnit {
        return proceedUnit {
            val roomId = request.roomId ?: ""
            val body = toJson(InviteBody(
                userId = request.userId ?: "",
                reason = request.reason,
            ))
            HttpRequest()
                .url("${uri}/_matrix/client/v3/rooms/${roomId}/invite")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .json(body)
                .post()
        }
    }

    override fun inviteBlocking(
        request: RoomsInviteRequest
    ): ResponseUnit {
        return toBlocking { invite(request) }
    }

    override suspend fun getJoinedRooms(): Response<RoomsGetJoinedRoomsResponse> {
        return proceed {
            HttpRequest()
                .url("${uri}/_matrix/client/v3/joined_rooms")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .get()
        }
    }

    override fun getJoinedRoomsBlocking(): Response<RoomsGetJoinedRoomsResponse> {
        return toBlocking { getJoinedRooms() }
    }

    override suspend fun getRoomName(roomId: String): Response<RoomsGetRoomNameResponse> {
        return proceed {
            HttpRequest()
                .url("${uri}/_matrix/client/v3/rooms/${roomId}/state/m.room.name")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .get()
        }
    }

    override fun getRoomNameBlocking(roomId: String): Response<RoomsGetRoomNameResponse> {
        return toBlocking { getRoomName(roomId) }
    }

    override suspend fun sendMessage(
        request: RoomsSendMessageRequest
    ): Response<RoomsSendMessageResponse> {
        return proceed {
            val roomId = request.roomId ?: ""
            val txnId = generateTxnId()
            val body = toJson(
                SendMessageBody(
                    msgtype = request.msgtype ?: "m.text",
                    body = request.body ?: "",
                )
            )
            HttpRequest()
                .url("${uri}/_matrix/client/v3/rooms/${roomId}/send/m.room.message/${txnId}")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .json(body)
                .put()
        }
    }

    override fun sendMessageBlocking(
        request: RoomsSendMessageRequest
    ): Response<RoomsSendMessageResponse> {
        return toBlocking { sendMessage(request) }
    }

    private fun generateTxnId(): String {
        txnCounter++
        return "kmatrix_${txnCounter}_${Random.nextLong(0, 100000)}"
    }

    @Serializable
    private data class CreateRoomBody(
        @SerialName("visibility")
        val visibility: String? = null,
        @SerialName("room_alias_name")
        val roomAliasName: String? = null,
        @SerialName("name")
        val name: String? = null,
        @SerialName("topic")
        val topic: String? = null,
        @SerialName("invite")
        val invite: Array<String>? = null,
        @SerialName("preset")
        val preset: String? = null,
        @SerialName("is_direct")
        val isDirect: Boolean? = null,
    )

    @Serializable
    private data class JoinRoomBody(
        @SerialName("reason")
        val reason: String? = null,
    )

    @Serializable
    private data class LeaveRoomBody(
        @SerialName("reason")
        val reason: String? = null,
    )

    @Serializable
    private data class InviteBody(
        @SerialName("user_id")
        val userId: String,
        @SerialName("reason")
        val reason: String? = null,
    )

    @Serializable
    private data class SendMessageBody(
        @SerialName("msgtype")
        val msgtype: String,
        @SerialName("body")
        val body: String,
    )
}
