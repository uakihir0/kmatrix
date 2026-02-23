package work.socialhub.kmatrix.api.response.notifications

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class NotificationsGetResponse {

    @SerialName("next_token")
    var nextToken: String? = null

    @SerialName("notifications")
    var notifications: Array<Notification> = arrayOf()

    @Serializable
    class Notification {
        @SerialName("actions")
        var actions: Array<kotlinx.serialization.json.JsonElement> = arrayOf()

        @SerialName("event")
        var event: Event = Event()

        @SerialName("profile_tag")
        var profileTag: String? = null

        @SerialName("read")
        var read: Boolean = false

        @SerialName("room_id")
        var roomId: String = ""

        @SerialName("ts")
        var ts: Long = 0
    }

    @Serializable
    class Event {
        @SerialName("content")
        var content: Map<String, kotlinx.serialization.json.JsonElement> = mapOf()

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
