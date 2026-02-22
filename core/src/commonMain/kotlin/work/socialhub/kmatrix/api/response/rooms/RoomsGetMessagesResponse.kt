package work.socialhub.kmatrix.api.response.rooms

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class RoomsGetMessagesResponse {

    @SerialName("start")
    var start: String = ""

    @SerialName("end")
    var end: String? = null

    @SerialName("chunk")
    var chunk: Array<RoomEvent> = arrayOf()

    @SerialName("state")
    var state: Array<RoomEvent>? = null
}

@JsExport
@Serializable
class RoomEvent {

    @SerialName("type")
    var type: String = ""

    @SerialName("event_id")
    var eventId: String = ""

    @SerialName("sender")
    var sender: String = ""

    @SerialName("origin_server_ts")
    var originServerTs: Long = 0

    @SerialName("content")
    var content: Map<String, @Serializable(with = work.socialhub.kmpcommon.AnySerializer::class) Any?> = mapOf()

    @SerialName("room_id")
    var roomId: String? = null

    @SerialName("state_key")
    var stateKey: String? = null

    @SerialName("unsigned")
    var unsigned: Map<String, @Serializable(with = work.socialhub.kmpcommon.AnySerializer::class) Any?>? = null
}
