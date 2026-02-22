package work.socialhub.kmatrix.internal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import work.socialhub.khttpclient.HttpRequest
import work.socialhub.kmatrix.api.RoomsResource
import work.socialhub.kmatrix.api.request.rooms.RoomsSendMessageRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.rooms.RoomsGetJoinedRoomsResponse
import work.socialhub.kmatrix.api.response.rooms.RoomsGetRoomNameResponse
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
    private data class SendMessageBody(
        @SerialName("msgtype")
        val msgtype: String,
        @SerialName("body")
        val body: String,
    )
}
