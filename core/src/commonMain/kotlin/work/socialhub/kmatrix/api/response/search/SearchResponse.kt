package work.socialhub.kmatrix.api.response.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class SearchResponse {

    @SerialName("search_categories")
    var searchCategories: SearchCategories? = null
}

@JsExport
@Serializable
class SearchCategories {

    @SerialName("room_events")
    var roomEvents: SearchRoomEvents? = null
}

@JsExport
@Serializable
class SearchRoomEvents {

    @SerialName("count")
    var count: Long? = null

    @SerialName("results")
    var results: Array<SearchResult> = arrayOf()

    @SerialName("next_batch")
    var nextBatch: String? = null
}

@JsExport
@Serializable
class SearchResult {

    @SerialName("rank")
    var rank: Double? = null

    @SerialName("result")
    var result: SearchResultEvent? = null
}

@JsExport
@Serializable
class SearchResultEvent {

    @SerialName("event_id")
    var eventId: String? = null

    @SerialName("type")
    var type: String? = null

    @SerialName("sender")
    var sender: String? = null

    @SerialName("room_id")
    var roomId: String? = null

    @SerialName("origin_server_ts")
    var originServerTs: Long? = null

    @SerialName("content")
    var content: SearchResultContent? = null
}

@JsExport
@Serializable
class SearchResultContent {

    @SerialName("msgtype")
    var msgtype: String? = null

    @SerialName("body")
    var body: String? = null
}
