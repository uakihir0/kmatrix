package work.socialhub.kmatrix.api.response.rooms

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class RoomsGetJoinedRoomsResponse {

    @SerialName("joined_rooms")
    var joinedRooms: Array<String> = arrayOf()
}
