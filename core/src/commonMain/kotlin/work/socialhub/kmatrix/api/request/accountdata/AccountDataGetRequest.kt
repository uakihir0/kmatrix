package work.socialhub.kmatrix.api.request.accountdata

import kotlin.js.JsExport

@JsExport
class AccountDataGetRequest {
    var userId: String? = null
    var roomId: String? = null
    var type: String? = null
}
