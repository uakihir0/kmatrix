package work.socialhub.kmatrix.api.response.presence

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class PresenceGetResponse {

    @SerialName("presence")
    var presence: String = ""

    @SerialName("last_active_ago")
    var lastActiveAgo: Long? = null

    @SerialName("status_msg")
    var statusMsg: String? = null

    @SerialName("currently_active")
    var currentlyActive: Boolean? = null
}
