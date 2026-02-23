package work.socialhub.kmatrix.api.request.accountdata

import kotlin.js.JsExport

@JsExport
class AccountDataSetRequest {
    var userId: String? = null
    var roomId: String? = null
    var type: String? = null
    /** JSON string of the account data content */
    var data: String? = null
}
