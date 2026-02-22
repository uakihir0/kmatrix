package work.socialhub.kmatrix.api.request.rooms

import kotlin.js.JsExport

@JsExport
class RoomsSetReadMarkersRequest {
    /** The room ID in which to set the read markers. */
    var roomId: String? = null
    /** The event ID the read marker should be located at (fully read position). */
    var fullyRead: String? = null
    /** The event ID to set the read receipt location at. */
    var read: String? = null
    /** The event ID to set the private read receipt location at. */
    var readPrivate: String? = null
}
