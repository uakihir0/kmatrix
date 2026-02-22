package work.socialhub.kmatrix.api.request.rooms

import kotlin.js.JsExport

@JsExport
class RoomsSendReceiptRequest {
    /** The room ID in which to send the receipt. */
    var roomId: String? = null
    /** The event ID to acknowledge up to. */
    var eventId: String? = null
    /** The type of receipt to send: "m.read", "m.read.private", "m.fully_read". */
    var receiptType: String? = "m.read"
    /** The thread ID, if applicable. "main" for main timeline. */
    var threadId: String? = null
}
