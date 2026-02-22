package work.socialhub.kmatrix.api.request.profile

import kotlin.js.JsExport

@JsExport
class ProfileSetAvatarUrlRequest {
    var userId: String? = null
    var avatarUrl: String? = null
}
