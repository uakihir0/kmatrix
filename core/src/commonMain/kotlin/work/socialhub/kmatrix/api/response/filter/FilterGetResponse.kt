package work.socialhub.kmatrix.api.response.filter

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class FilterGetResponse {

    @SerialName("event_fields")
    var eventFields: Array<String>? = null

    @SerialName("event_format")
    var eventFormat: String? = null

    @SerialName("presence")
    var presence: EventFilter? = null

    @SerialName("account_data")
    var accountData: EventFilter? = null

    @SerialName("room")
    var room: RoomFilter? = null
}

@JsExport
@Serializable
class EventFilter {

    @SerialName("limit")
    var limit: Int? = null

    @SerialName("not_senders")
    var notSenders: Array<String>? = null

    @SerialName("not_types")
    var notTypes: Array<String>? = null

    @SerialName("senders")
    var senders: Array<String>? = null

    @SerialName("types")
    var types: Array<String>? = null
}

@JsExport
@Serializable
class RoomFilter {

    @SerialName("not_rooms")
    var notRooms: Array<String>? = null

    @SerialName("rooms")
    var rooms: Array<String>? = null

    @SerialName("ephemeral")
    var ephemeral: RoomEventFilter? = null

    @SerialName("include_leave")
    var includeLeave: Boolean? = null

    @SerialName("state")
    var state: RoomEventFilter? = null

    @SerialName("timeline")
    var timeline: RoomEventFilter? = null

    @SerialName("account_data")
    var accountData: RoomEventFilter? = null
}

@JsExport
@Serializable
class RoomEventFilter {

    @SerialName("limit")
    var limit: Int? = null

    @SerialName("not_senders")
    var notSenders: Array<String>? = null

    @SerialName("not_types")
    var notTypes: Array<String>? = null

    @SerialName("senders")
    var senders: Array<String>? = null

    @SerialName("types")
    var types: Array<String>? = null

    @SerialName("not_rooms")
    var notRooms: Array<String>? = null

    @SerialName("rooms")
    var rooms: Array<String>? = null

    @SerialName("contains_url")
    var containsUrl: Boolean? = null

    @SerialName("include_redundant_members")
    var includeRedundantMembers: Boolean? = null

    @SerialName("lazy_load_members")
    var lazyLoadMembers: Boolean? = null
}
