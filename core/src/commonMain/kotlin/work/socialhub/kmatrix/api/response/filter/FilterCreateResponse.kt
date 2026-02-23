package work.socialhub.kmatrix.api.response.filter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class FilterCreateResponse {

    @SerialName("filter_id")
    var filterId: String = ""
}
