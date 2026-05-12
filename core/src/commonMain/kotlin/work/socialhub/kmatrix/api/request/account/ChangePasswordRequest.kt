package work.socialhub.kmatrix.api.request.account

import kotlin.js.JsExport

@JsExport
class ChangePasswordRequest {
    var auth: String? = null
    var newPassword: String? = null
    var session: String? = null
    var logoutDevices: Boolean? = null
    var logoutDevicesAll: Boolean? = null
}
