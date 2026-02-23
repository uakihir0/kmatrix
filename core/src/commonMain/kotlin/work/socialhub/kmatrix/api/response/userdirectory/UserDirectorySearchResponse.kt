package work.socialhub.kmatrix.api.response.userdirectory

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class UserDirectorySearchResponse {

    @SerialName("results")
    var results: Array<UserDirectoryUser> = arrayOf()

    @SerialName("limited")
    var limited: Boolean = false
}

@JsExport
@Serializable
class UserDirectoryUser {

    @SerialName("user_id")
    var userId: String = ""

    @SerialName("display_name")
    var displayName: String? = null

    @SerialName("avatar_url")
    var avatarUrl: String? = null
}
