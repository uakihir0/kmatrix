package work.socialhub.kmatrix.api.request.sync

import kotlin.js.JsExport

@JsExport
class SyncRequest {
    /** A point in time to continue a sync from (the next_batch from a previous sync). */
    var since: String? = null
    /** The maximum time to wait, in milliseconds, before returning this request. */
    var timeout: Int? = null
    /** A filter ID or inline JSON filter to apply. */
    var filter: String? = null
    /** Controls whether to include the full state for all rooms. */
    var fullState: Boolean? = null
    /** Controls presence status: "offline" to not update presence. */
    var setPresence: String? = null
}
