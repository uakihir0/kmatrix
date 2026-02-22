package work.socialhub.kmatrix.api.response.accounts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class AccountsWhoamiResponse {

    @SerialName("user_id")
    lateinit var userId: String

    @SerialName("device_id")
    var deviceId: String? = null

    @SerialName("is_guest")
    var isGuest: Boolean = false
}
