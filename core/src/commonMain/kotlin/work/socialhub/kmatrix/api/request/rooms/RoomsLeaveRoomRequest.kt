package work.socialhub.kmatrix.api.request.rooms

import kotlin.js.JsExport

@JsExport
class RoomsLeaveRoomRequest {
    /** The room ID to leave. */
    var roomId: String? = null
    /** The reason the user is leaving the room. */
    var reason: String? = null
}
