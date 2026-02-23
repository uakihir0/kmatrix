package work.socialhub.kmatrix.internal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import work.socialhub.khttpclient.HttpRequest
import work.socialhub.kmatrix.api.SearchResource
import work.socialhub.kmatrix.api.request.search.SearchRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.search.SearchResponse
import work.socialhub.kmatrix.internal.InternalUtility.toJson
import work.socialhub.kmatrix.util.Headers.AUTHORIZATION
import work.socialhub.kmatrix.util.MediaType
import work.socialhub.kmatrix.util.toBlocking

class SearchResourceImpl(
    uri: String,
    accessToken: String,
) : AbstractAuthResourceImpl(uri, accessToken),
    SearchResource {

    override suspend fun search(
        request: SearchRequest
    ): Response<SearchResponse> {
        return proceed {
            val roomEvents = SearchRoomEventsBody(
                searchTerm = request.searchTerm ?: "",
                keys = request.keys,
                orderBy = request.orderBy,
                filter = if (request.roomIds != null) {
                    SearchFilterBody(rooms = request.roomIds)
                } else null,
            )
            val body = toJson(
                SearchBody(
                    searchCategories = SearchCategoriesBody(
                        roomEvents = roomEvents,
                    )
                )
            )
            HttpRequest()
                .url("${uri}/_matrix/client/v3/search")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .qwn("next_batch", request.nextBatch)
                .json(body)
                .post()
        }
    }

    override fun searchBlocking(
        request: SearchRequest
    ): Response<SearchResponse> {
        return toBlocking { search(request) }
    }

    @Serializable
    private data class SearchBody(
        @SerialName("search_categories")
        val searchCategories: SearchCategoriesBody,
    )

    @Serializable
    private data class SearchCategoriesBody(
        @SerialName("room_events")
        val roomEvents: SearchRoomEventsBody,
    )

    @Serializable
    private data class SearchRoomEventsBody(
        @SerialName("search_term")
        val searchTerm: String,
        @SerialName("keys")
        val keys: Array<String>? = null,
        @SerialName("order_by")
        val orderBy: String? = null,
        @SerialName("filter")
        val filter: SearchFilterBody? = null,
    )

    @Serializable
    private data class SearchFilterBody(
        @SerialName("rooms")
        val rooms: Array<String>? = null,
    )
}
