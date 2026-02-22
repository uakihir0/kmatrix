package work.socialhub.kmatrix.api.request.rooms

import kotlin.js.JsExport

@JsExport
class RoomsCreateRoomRequest {
    /** A public visibility means that the room will be shown in the published room list. */
    var visibility: String? = null
    /** The desired room alias local part. */
    var roomAliasName: String? = null
    /** The room name. */
    var name: String? = null
    /** The room topic. */
    var topic: String? = null
    /** A list of user IDs to invite to the room. */
    var invite: Array<String>? = null
    /** Convenience parameter for setting room preset: private_chat, public_chat, trusted_private_chat */
    var preset: String? = null
    /** Whether all members of the room (not combinated with public join_rules) should be invited. */
    var isDirect: Boolean? = null
}
