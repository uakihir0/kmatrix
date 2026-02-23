package work.socialhub.kmatrix.api.response.media

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class MediaGetConfigResponse {

    @SerialName("m.upload.size")
    var uploadSize: Long? = null
}
