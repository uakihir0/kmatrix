package work.socialhub.kmatrix.api.request.relations

import kotlin.js.JsExport

@JsExport
class RelationsGetRequest {
    var roomId: String? = null
    var eventId: String? = null
    /** Optional relation type filter (e.g. "m.annotation", "m.thread") */
    var relType: String? = null
    /** Optional event type filter (e.g. "m.reaction") */
    var eventType: String? = null
    var from: String? = null
    var to: String? = null
    var limit: Int? = null
    var dir: String? = null
}
