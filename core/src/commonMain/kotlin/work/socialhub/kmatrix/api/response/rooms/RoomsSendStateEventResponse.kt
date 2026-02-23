package work.socialhub.kmatrix.api.response.rooms

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class RoomsSendStateEventResponse {

    @SerialName("event_id")
    var eventId: String = ""
}
