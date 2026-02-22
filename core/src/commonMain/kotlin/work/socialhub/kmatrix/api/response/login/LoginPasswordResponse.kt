package work.socialhub.kmatrix.api.response.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class LoginPasswordResponse {

    @SerialName("user_id")
    lateinit var userId: String

    @SerialName("access_token")
    lateinit var accessToken: String

    @SerialName("device_id")
    lateinit var deviceId: String

    @SerialName("home_server")
    var homeServer: String? = null
}
