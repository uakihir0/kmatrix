package work.socialhub.kmatrix.api.request.tags

import kotlin.js.JsExport

@JsExport
class TagsSetRequest {
    var userId: String? = null
    var roomId: String? = null

    /** The tag to add (e.g., "m.favourite", "m.lowpriority", or custom). */
    var tag: String? = null

    /** A number in a range [0,1] describing a relative position of the room. */
    var order: Double? = null
}
