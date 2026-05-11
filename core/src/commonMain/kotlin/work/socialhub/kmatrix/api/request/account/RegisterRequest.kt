package work.socialhub.kmatrix.api.request.account

import kotlin.js.JsExport

@JsExport
class RegisterRequest {
    var username: String? = null
    var password: String? = null
    var initialDeviceDisplayName: String? = null
    var auth: String? = null
    var session: String? = null
    var did: String? = null
    var bindEmail: Boolean? = null
}
