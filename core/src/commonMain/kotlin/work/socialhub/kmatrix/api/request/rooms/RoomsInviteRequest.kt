package work.socialhub.kmatrix.api.request.rooms

import kotlin.js.JsExport

@JsExport
class RoomsInviteRequest {
    /** The room ID to which to invite the user. */
    var roomId: String? = null
    /** The fully qualified user ID of the invitee. */
    var userId: String? = null
    /** The reason the invitation was sent. */
    var reason: String? = null
}
