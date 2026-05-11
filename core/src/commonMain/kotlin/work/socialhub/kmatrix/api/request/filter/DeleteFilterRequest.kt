package work.socialhub.kmatrix.api.request.filter

import kotlin.js.JsExport

@JsExport
class DeleteFilterRequest {
    var userId: String? = null
    var filterId: String? = null
}
