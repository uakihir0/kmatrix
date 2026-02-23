package work.socialhub.kmatrix.api.response.directory

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class DirectoryResolveAliasResponse {

    @SerialName("room_id")
    var roomId: String = ""

    @SerialName("servers")
    var servers: Array<String> = arrayOf()
}
