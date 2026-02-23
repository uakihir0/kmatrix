package work.socialhub.kmatrix.api.response.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlin.js.JsExport

@JsExport
@Serializable
class EventsGetEventResponse {

    @SerialName("content")
    var content: Map<String, JsonElement> = mapOf()

    @SerialName("event_id")
    var eventId: String = ""

    @SerialName("origin_server_ts")
    var originServerTs: Long = 0

    @SerialName("room_id")
    var roomId: String = ""

    @SerialName("sender")
    var sender: String = ""

    @SerialName("type")
    var type: String = ""

    @SerialName("state_key")
    var stateKey: String? = null

    @SerialName("unsigned")
    var unsigned: Map<String, JsonElement>? = null
}
