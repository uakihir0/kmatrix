package work.socialhub.kmatrix.api

import work.socialhub.kmatrix.api.request.rooms.RoomsCreateRoomRequest
import work.socialhub.kmatrix.api.request.rooms.RoomsSendMessageRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.rooms.RoomsCreateRoomResponse
import work.socialhub.kmatrix.api.response.rooms.RoomsGetJoinedRoomsResponse
import work.socialhub.kmatrix.api.response.rooms.RoomsGetRoomNameResponse
import work.socialhub.kmatrix.api.response.rooms.RoomsSendMessageResponse
import kotlin.js.JsExport

@JsExport
interface RoomsResource {

    /**
     * POST /_matrix/client/v3/createRoom
     * Create a new room with various configuration options.
     */
    suspend fun createRoom(
        request: RoomsCreateRoomRequest
    ): Response<RoomsCreateRoomResponse>

    @JsExport.Ignore
    fun createRoomBlocking(
        request: RoomsCreateRoomRequest
    ): Response<RoomsCreateRoomResponse>

    suspend fun getJoinedRooms(): Response<RoomsGetJoinedRoomsResponse>

    @JsExport.Ignore
    fun getJoinedRoomsBlocking(): Response<RoomsGetJoinedRoomsResponse>

    suspend fun getRoomName(roomId: String): Response<RoomsGetRoomNameResponse>

    @JsExport.Ignore
    fun getRoomNameBlocking(roomId: String): Response<RoomsGetRoomNameResponse>

    suspend fun sendMessage(
        request: RoomsSendMessageRequest
    ): Response<RoomsSendMessageResponse>

    @JsExport.Ignore
    fun sendMessageBlocking(
        request: RoomsSendMessageRequest
    ): Response<RoomsSendMessageResponse>
}
