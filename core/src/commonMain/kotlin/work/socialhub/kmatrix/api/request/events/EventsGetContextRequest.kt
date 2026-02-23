package work.socialhub.kmatrix.api.request.events

import kotlin.js.JsExport

@JsExport
class EventsGetContextRequest {
    var roomId: String? = null
    var eventId: String? = null
    var limit: Int? = null
    var filter: String? = null
}
