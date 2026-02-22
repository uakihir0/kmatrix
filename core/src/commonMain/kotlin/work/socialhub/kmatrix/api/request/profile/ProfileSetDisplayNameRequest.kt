package work.socialhub.kmatrix.api.request.profile

import kotlin.js.JsExport

@JsExport
class ProfileSetDisplayNameRequest {
    var userId: String? = null
    var displayname: String? = null
}
