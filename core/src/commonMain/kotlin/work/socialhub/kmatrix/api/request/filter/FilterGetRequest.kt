package work.socialhub.kmatrix.api.request.filter

import kotlin.js.JsExport

@JsExport
class FilterGetRequest {
    var userId: String? = null
    var filterId: String? = null
}
