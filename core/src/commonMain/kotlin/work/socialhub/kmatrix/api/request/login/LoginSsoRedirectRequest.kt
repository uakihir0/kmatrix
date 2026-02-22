package work.socialhub.kmatrix.api.request.login

import kotlin.js.JsExport

@JsExport
class LoginSsoRedirectRequest {
    /** The URL to redirect to after SSO authentication */
    var redirectUrl: String? = null
}
