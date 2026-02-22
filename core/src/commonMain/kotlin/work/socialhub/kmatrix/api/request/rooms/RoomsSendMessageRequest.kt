package work.socialhub.kmatrix.api.request.rooms

import kotlin.js.JsExport

@JsExport
class RoomsSendMessageRequest {
    var roomId: String? = null
    var body: String? = null
    var msgtype: String? = "m.text"
}
