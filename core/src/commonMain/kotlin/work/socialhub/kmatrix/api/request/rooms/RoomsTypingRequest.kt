package work.socialhub.kmatrix.api.request.rooms

import kotlin.js.JsExport

@JsExport
class RoomsTypingRequest {
    /** The room ID to set typing notification in. */
    var roomId: String? = null
    /** The user ID to set typing notification for. */
    var userId: String? = null
    /** Whether the user is typing. */
    var typing: Boolean? = null
    /** The length of time in milliseconds to mark this user as typing. */
    var timeout: Int? = null
}
