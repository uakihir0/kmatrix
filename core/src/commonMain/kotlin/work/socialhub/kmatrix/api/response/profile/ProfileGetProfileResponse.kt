package work.socialhub.kmatrix.api.response.profile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class ProfileGetProfileResponse {

    @SerialName("displayname")
    var displayname: String? = null

    @SerialName("avatar_url")
    var avatarUrl: String? = null
}
