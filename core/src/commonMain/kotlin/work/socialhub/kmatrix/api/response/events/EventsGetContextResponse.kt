package work.socialhub.kmatrix.api.response.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlin.js.JsExport

@JsExport
@Serializable
class EventsGetContextResponse {

    @SerialName("start")
    var start: String? = null

    @SerialName("end")
    var end: String? = null

    @SerialName("event")
    var event: ContextEvent? = null

    @SerialName("events_before")
    var eventsBefore: Array<ContextEvent> = arrayOf()

    @SerialName("events_after")
    var eventsAfter: Array<ContextEvent> = arrayOf()

    @SerialName("state")
    var state: Array<ContextEvent> = arrayOf()

    @Serializable
    class ContextEvent {
        @SerialName("content")
        var content: Map<String, JsonElement> = mapOf()

        @SerialName("event_id")
        var eventId: String = ""

        @SerialName("origin_server_ts")
        var originServerTs: Long = 0

        @SerialName("room_id")
        var roomId: String? = null

        @SerialName("sender")
        var sender: String = ""

        @SerialName("type")
        var type: String = ""

        @SerialName("state_key")
        var stateKey: String? = null
    }
}
