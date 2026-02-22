package work.socialhub.kmatrix.api.request.rooms

import kotlin.js.JsExport

@JsExport
class RoomsJoinRoomRequest {
    /** The room ID or room alias to join. */
    var roomIdOrAlias: String? = null
    /** The reason the user is joining the room. */
    var reason: String? = null
}
