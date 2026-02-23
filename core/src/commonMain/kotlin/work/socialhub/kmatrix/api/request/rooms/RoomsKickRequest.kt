package work.socialhub.kmatrix.api.request.rooms

import kotlin.js.JsExport

@JsExport
class RoomsKickRequest {
    var roomId: String? = null
    var userId: String? = null
    var reason: String? = null
}
