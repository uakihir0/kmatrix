package work.socialhub.kmatrix.api.response.versions

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class VersionsGetResponse {

    @SerialName("versions")
    var versions: Array<String> = arrayOf()

    @SerialName("unstable_features")
    var unstableFeatures: Map<String, Boolean>? = null
}
