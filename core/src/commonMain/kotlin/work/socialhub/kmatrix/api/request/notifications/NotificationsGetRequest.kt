package work.socialhub.kmatrix.api.request.notifications

import kotlin.js.JsExport

@JsExport
class NotificationsGetRequest {
    var from: String? = null
    var limit: Int? = null
    var only: String? = null
}
