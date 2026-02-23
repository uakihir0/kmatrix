package work.socialhub.kmatrix.api.response.voip

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class VoIPGetTurnServerResponse {

    @SerialName("username")
    var username: String = ""

    @SerialName("password")
    var password: String = ""

    @SerialName("uris")
    var uris: Array<String> = arrayOf()

    @SerialName("ttl")
    var ttl: Int = 0
}
