package work.socialhub.kmatrix.api.request.rooms

import kotlin.js.JsExport

@JsExport
class RoomsSendStateEventRequest {
    var roomId: String? = null

    /** The type of state event to send (e.g., "m.room.name", "m.room.topic"). */
    var eventType: String? = null

    /** The state key. Defaults to empty string. */
    var stateKey: String? = null

    /** The JSON body content of the state event. */
    var body: String? = null
}
