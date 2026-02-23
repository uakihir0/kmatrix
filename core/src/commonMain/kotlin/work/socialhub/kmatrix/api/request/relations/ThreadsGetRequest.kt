package work.socialhub.kmatrix.api.request.relations

import kotlin.js.JsExport

@JsExport
class ThreadsGetRequest {
    var roomId: String? = null
    var include: String? = null
    var limit: Int? = null
    var from: String? = null
}
