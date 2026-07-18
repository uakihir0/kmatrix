package work.socialhub.kmatrix.api.request.rooms

import kotlin.js.JsExport

@JsExport
class RoomsSendReactionRequest {
    var roomId: String? = null
    var eventId: String? = null
    var key: String? = null
}
