package work.socialhub.kmatrix.api.response.push

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class PushRulesGetEnabledResponse {

    @SerialName("enabled")
    var enabled: Boolean = false
}
