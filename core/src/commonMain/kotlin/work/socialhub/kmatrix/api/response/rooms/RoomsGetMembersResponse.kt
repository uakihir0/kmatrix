package work.socialhub.kmatrix.api.response.rooms

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class RoomsGetMembersResponse {

    @SerialName("chunk")
    var chunk: Array<RoomEvent> = arrayOf()
}
