package work.socialhub.kmatrix.api.request.presence

import kotlin.js.JsExport

@JsExport
class PresenceSetRequest {
    var userId: String? = null

    /** Required. The new presence state. One of: "online", "offline", "unavailable". */
    var presence: String? = null

    /** The status message to attach to this state. */
    var statusMsg: String? = null
}
