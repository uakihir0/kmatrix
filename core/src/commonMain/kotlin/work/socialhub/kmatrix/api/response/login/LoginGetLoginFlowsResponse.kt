package work.socialhub.kmatrix.api.response.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class LoginGetLoginFlowsResponse {

    @SerialName("flows")
    var flows: Array<LoginFlow> = arrayOf()
}

@JsExport
@Serializable
class LoginFlow {

    @SerialName("type")
    lateinit var type: String
}
