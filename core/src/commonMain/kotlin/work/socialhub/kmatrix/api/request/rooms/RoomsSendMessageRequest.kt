package work.socialhub.kmatrix.api.request.rooms

import kotlin.js.JsExport

@JsExport
class RoomsSendMessageRequest {
    var roomId: String? = null
    var body: String? = null
    var msgtype: String? = "m.text"

    var replyTo: String? = null

    var url: String? = null
    var filename: String? = null
    var mimetype: String? = null
    var thumbnailUrl: String? = null
    var thumbnailMimetype: String? = null
    var thumbnailSize: Long? = null
    var width: Long? = null
    var height: Long? = null
    var duration: Long? = null

    var geoUri: String? = null
    var description: String? = null

    var relatesToType: String? = null
    var relatesToEventId: String? = null
    var relatesToKey: String? = null
    var relatesToRelType: String? = null
}
