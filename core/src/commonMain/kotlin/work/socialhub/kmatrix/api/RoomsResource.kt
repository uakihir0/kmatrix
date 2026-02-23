package work.socialhub.kmatrix.api

import work.socialhub.kmatrix.api.request.rooms.RoomsBanRequest
import work.socialhub.kmatrix.api.request.rooms.RoomsCreateRoomRequest
import work.socialhub.kmatrix.api.request.rooms.RoomsGetMessagesRequest
import work.socialhub.kmatrix.api.request.rooms.RoomsInviteRequest
import work.socialhub.kmatrix.api.request.rooms.RoomsJoinRoomRequest
import work.socialhub.kmatrix.api.request.rooms.RoomsKickRequest
import work.socialhub.kmatrix.api.request.rooms.RoomsForgetRoomRequest
import work.socialhub.kmatrix.api.request.rooms.RoomsLeaveRoomRequest
import work.socialhub.kmatrix.api.request.rooms.RoomsRedactEventRequest
import work.socialhub.kmatrix.api.request.rooms.RoomsSendMessageRequest
import work.socialhub.kmatrix.api.request.rooms.RoomsSendReceiptRequest
import work.socialhub.kmatrix.api.request.rooms.RoomsSendStateEventRequest
import work.socialhub.kmatrix.api.request.rooms.RoomsSetReadMarkersRequest
import work.socialhub.kmatrix.api.request.rooms.RoomsTypingRequest
import work.socialhub.kmatrix.api.request.rooms.RoomsUnbanRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.ResponseUnit
import work.socialhub.kmatrix.api.response.rooms.RoomsCreateRoomResponse
import work.socialhub.kmatrix.api.response.rooms.RoomsGetJoinedMembersResponse
import work.socialhub.kmatrix.api.response.rooms.RoomsGetJoinedRoomsResponse
import work.socialhub.kmatrix.api.response.rooms.RoomsGetMembersResponse
import work.socialhub.kmatrix.api.response.rooms.RoomsGetMessagesResponse
import work.socialhub.kmatrix.api.response.rooms.RoomsGetRoomNameResponse
import work.socialhub.kmatrix.api.response.rooms.RoomsJoinRoomResponse
import work.socialhub.kmatrix.api.response.rooms.RoomsRedactEventResponse
import work.socialhub.kmatrix.api.response.rooms.RoomsSendMessageResponse
import work.socialhub.kmatrix.api.response.rooms.RoomsSendStateEventResponse
import work.socialhub.kmatrix.api.response.rooms.RoomsStateEvent
import kotlin.js.JsExport

@JsExport
interface RoomsResource {

    /**
     * POST /_matrix/client/v3/createRoom
     */
    suspend fun createRoom(
        request: RoomsCreateRoomRequest
    ): Response<RoomsCreateRoomResponse>

    @JsExport.Ignore
    fun createRoomBlocking(
        request: RoomsCreateRoomRequest
    ): Response<RoomsCreateRoomResponse>

    /**
     * POST /_matrix/client/v3/join/{roomIdOrAlias}
     */
    suspend fun joinRoom(
        request: RoomsJoinRoomRequest
    ): Response<RoomsJoinRoomResponse>

    @JsExport.Ignore
    fun joinRoomBlocking(
        request: RoomsJoinRoomRequest
    ): Response<RoomsJoinRoomResponse>

    /**
     * POST /_matrix/client/v3/rooms/{roomId}/leave
     */
    suspend fun leaveRoom(
        request: RoomsLeaveRoomRequest
    ): ResponseUnit

    @JsExport.Ignore
    fun leaveRoomBlocking(
        request: RoomsLeaveRoomRequest
    ): ResponseUnit

    /**
     * POST /_matrix/client/v3/rooms/{roomId}/invite
     */
    suspend fun invite(
        request: RoomsInviteRequest
    ): ResponseUnit

    @JsExport.Ignore
    fun inviteBlocking(
        request: RoomsInviteRequest
    ): ResponseUnit

    suspend fun getJoinedRooms(): Response<RoomsGetJoinedRoomsResponse>

    @JsExport.Ignore
    fun getJoinedRoomsBlocking(): Response<RoomsGetJoinedRoomsResponse>

    suspend fun getRoomName(roomId: String): Response<RoomsGetRoomNameResponse>

    @JsExport.Ignore
    fun getRoomNameBlocking(roomId: String): Response<RoomsGetRoomNameResponse>

    /**
     * GET /_matrix/client/v3/rooms/{roomId}/members
     */
    suspend fun getMembers(roomId: String): Response<RoomsGetMembersResponse>

    @JsExport.Ignore
    fun getMembersBlocking(roomId: String): Response<RoomsGetMembersResponse>

    /**
     * GET /_matrix/client/v3/rooms/{roomId}/joined_members
     */
    suspend fun getJoinedMembers(roomId: String): Response<RoomsGetJoinedMembersResponse>

    @JsExport.Ignore
    fun getJoinedMembersBlocking(roomId: String): Response<RoomsGetJoinedMembersResponse>

    /**
     * GET /_matrix/client/v3/rooms/{roomId}/messages
     */
    suspend fun getMessages(
        request: RoomsGetMessagesRequest
    ): Response<RoomsGetMessagesResponse>

    @JsExport.Ignore
    fun getMessagesBlocking(
        request: RoomsGetMessagesRequest
    ): Response<RoomsGetMessagesResponse>

    suspend fun sendMessage(
        request: RoomsSendMessageRequest
    ): Response<RoomsSendMessageResponse>

    @JsExport.Ignore
    fun sendMessageBlocking(
        request: RoomsSendMessageRequest
    ): Response<RoomsSendMessageResponse>

    /**
     * PUT /_matrix/client/v3/rooms/{roomId}/redact/{eventId}/{txnId}
     */
    suspend fun redactEvent(
        request: RoomsRedactEventRequest
    ): Response<RoomsRedactEventResponse>

    @JsExport.Ignore
    fun redactEventBlocking(
        request: RoomsRedactEventRequest
    ): Response<RoomsRedactEventResponse>

    /**
     * PUT /_matrix/client/v3/rooms/{roomId}/typing/{userId}
     */
    suspend fun setTyping(
        request: RoomsTypingRequest
    ): ResponseUnit

    @JsExport.Ignore
    fun setTypingBlocking(
        request: RoomsTypingRequest
    ): ResponseUnit

    /**
     * POST /_matrix/client/v3/rooms/{roomId}/receipt/{receiptType}/{eventId}
     */
    suspend fun sendReceipt(
        request: RoomsSendReceiptRequest
    ): ResponseUnit

    @JsExport.Ignore
    fun sendReceiptBlocking(
        request: RoomsSendReceiptRequest
    ): ResponseUnit

    /**
     * POST /_matrix/client/v3/rooms/{roomId}/read_markers
     */
    suspend fun setReadMarkers(
        request: RoomsSetReadMarkersRequest
    ): ResponseUnit

    @JsExport.Ignore
    fun setReadMarkersBlocking(
        request: RoomsSetReadMarkersRequest
    ): ResponseUnit

    /**
     * POST /_matrix/client/v3/rooms/{roomId}/ban
     * Ban a user from the room.
     */
    suspend fun ban(request: RoomsBanRequest): ResponseUnit

    @JsExport.Ignore
    fun banBlocking(request: RoomsBanRequest): ResponseUnit

    /**
     * POST /_matrix/client/v3/rooms/{roomId}/unban
     * Unban a user from the room.
     */
    suspend fun unban(request: RoomsUnbanRequest): ResponseUnit

    @JsExport.Ignore
    fun unbanBlocking(request: RoomsUnbanRequest): ResponseUnit

    /**
     * POST /_matrix/client/v3/rooms/{roomId}/kick
     * Kick a user from the room.
     */
    suspend fun kick(request: RoomsKickRequest): ResponseUnit

    @JsExport.Ignore
    fun kickBlocking(request: RoomsKickRequest): ResponseUnit

    /**
     * GET /_matrix/client/v3/rooms/{roomId}/state/{eventType}/{stateKey}
     * Get a state event in a room.
     */
    suspend fun getStateEvent(
        roomId: String,
        eventType: String,
        stateKey: String = ""
    ): Response<String>

    @JsExport.Ignore
    fun getStateEventBlocking(
        roomId: String,
        eventType: String,
        stateKey: String = ""
    ): Response<String>

    /**
     * PUT /_matrix/client/v3/rooms/{roomId}/state/{eventType}/{stateKey}
     * Send a state event to the given room.
     */
    suspend fun sendStateEvent(
        request: RoomsSendStateEventRequest
    ): Response<RoomsSendStateEventResponse>

    @JsExport.Ignore
    fun sendStateEventBlocking(
        request: RoomsSendStateEventRequest
    ): Response<RoomsSendStateEventResponse>

    /**
     * POST /_matrix/client/v3/rooms/{roomId}/forget
     * Stop remembering a particular room (remove from room list).
     */
    suspend fun forgetRoom(
        request: RoomsForgetRoomRequest
    ): ResponseUnit

    @JsExport.Ignore
    fun forgetRoomBlocking(
        request: RoomsForgetRoomRequest
    ): ResponseUnit

    /**
     * GET /_matrix/client/v3/rooms/{roomId}/state
     * Get all state events in a room.
     */
    suspend fun getState(
        roomId: String
    ): Response<Array<RoomsStateEvent>>

    @JsExport.Ignore
    fun getStateBlocking(
        roomId: String
    ): Response<Array<RoomsStateEvent>>
}
