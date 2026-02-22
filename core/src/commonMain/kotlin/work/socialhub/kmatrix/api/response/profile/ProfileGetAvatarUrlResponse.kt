package work.socialhub.kmatrix.api.response.profile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class ProfileGetAvatarUrlResponse {

    @SerialName("avatar_url")
    var avatarUrl: String? = null
}
