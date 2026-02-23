package work.socialhub.kmatrix.api.request.rooms

import kotlin.js.JsExport

@JsExport
class RoomsUnbanRequest {
    var roomId: String? = null
    var userId: String? = null
    var reason: String? = null
}
