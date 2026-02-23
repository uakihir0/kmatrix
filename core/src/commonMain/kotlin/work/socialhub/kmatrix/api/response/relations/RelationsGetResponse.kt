package work.socialhub.kmatrix.api.response.relations

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlin.js.JsExport

@JsExport
@Serializable
class RelationsGetResponse {

    @SerialName("chunk")
    var chunk: Array<RelationEvent> = arrayOf()

    @SerialName("next_batch")
    var nextBatch: String? = null

    @SerialName("prev_batch")
    var prevBatch: String? = null

    @Serializable
    class RelationEvent {
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
