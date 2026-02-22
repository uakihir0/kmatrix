package work.socialhub.kmatrix.api.request.rooms

import kotlin.js.JsExport

@JsExport
class RoomsRedactEventRequest {
    /** The room ID of the event to redact. */
    var roomId: String? = null
    /** The event ID to redact. */
    var eventId: String? = null
    /** The reason for the event being redacted. */
    var reason: String? = null
}
