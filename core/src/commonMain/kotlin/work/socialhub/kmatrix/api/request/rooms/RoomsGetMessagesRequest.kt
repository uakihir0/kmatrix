package work.socialhub.kmatrix.api.request.rooms

import kotlin.js.JsExport

@JsExport
class RoomsGetMessagesRequest {
    /** The room ID to get messages from. */
    var roomId: String? = null
    /** The token to start returning events from (pagination token from a previous response). */
    var from: String? = null
    /** The token to stop returning events at. */
    var to: String? = null
    /** The direction to return events from: "b" (backwards) or "f" (forwards). */
    var dir: String? = "b"
    /** The maximum number of events to return. */
    var limit: Int? = null
    /** A JSON RoomEventFilter to filter returned events with. */
    var filter: String? = null
}
