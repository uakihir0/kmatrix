package work.socialhub.kmatrix.api.request.filter

import kotlin.js.JsExport

@JsExport
class FilterCreateRequest {
    var userId: String? = null

    /** List of event types to include. */
    var eventTypes: Array<String>? = null

    /** List of event types to exclude. */
    var notEventTypes: Array<String>? = null

    /** List of room IDs to include. */
    var rooms: Array<String>? = null

    /** List of room IDs to exclude. */
    var notRooms: Array<String>? = null

    /** List of sender IDs to include. */
    var senders: Array<String>? = null

    /** List of sender IDs to exclude. */
    var notSenders: Array<String>? = null

    /** Maximum number of events to return. */
    var limit: Int? = null

    /** Include rooms the user has left. */
    var includeLeave: Boolean? = null
}
