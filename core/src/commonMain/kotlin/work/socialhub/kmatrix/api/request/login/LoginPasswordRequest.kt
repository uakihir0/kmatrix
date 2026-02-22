package work.socialhub.kmatrix.api.request.login

import kotlin.js.JsExport

@JsExport
class LoginPasswordRequest {
    /** User ID (e.g. @user:matrix.org) or just the localpart (e.g. user) */
    var user: String? = null
    var password: String? = null
    /** Optional device display name */
    var initialDeviceDisplayName: String? = null
}
