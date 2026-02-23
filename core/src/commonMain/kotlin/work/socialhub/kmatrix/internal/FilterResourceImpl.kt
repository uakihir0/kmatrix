package work.socialhub.kmatrix.internal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import work.socialhub.khttpclient.HttpRequest
import work.socialhub.kmatrix.api.FilterResource
import work.socialhub.kmatrix.api.request.filter.FilterCreateRequest
import work.socialhub.kmatrix.api.request.filter.FilterGetRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.filter.FilterCreateResponse
import work.socialhub.kmatrix.api.response.filter.FilterGetResponse
import work.socialhub.kmatrix.internal.InternalUtility.toJson
import work.socialhub.kmatrix.util.Headers.AUTHORIZATION
import work.socialhub.kmatrix.util.MediaType
import work.socialhub.kmatrix.util.toBlocking

class FilterResourceImpl(
    uri: String,
    accessToken: String,
) : AbstractAuthResourceImpl(uri, accessToken),
    FilterResource {

    override suspend fun createFilter(
        request: FilterCreateRequest
    ): Response<FilterCreateResponse> {
        return proceed {
            val userId = request.userId ?: ""
            val roomFilter = if (
                request.rooms != null ||
                request.notRooms != null ||
                request.limit != null ||
                request.eventTypes != null ||
                request.notEventTypes != null ||
                request.includeLeave != null
            ) {
                CreateFilterRoomFilter(
                    rooms = request.rooms,
                    notRooms = request.notRooms,
                    includeLeave = request.includeLeave,
                    timeline = if (
                        request.eventTypes != null ||
                        request.notEventTypes != null ||
                        request.senders != null ||
                        request.notSenders != null ||
                        request.limit != null
                    ) {
                        CreateFilterRoomEventFilter(
                            types = request.eventTypes,
                            notTypes = request.notEventTypes,
                            senders = request.senders,
                            notSenders = request.notSenders,
                            limit = request.limit,
                        )
                    } else null,
                )
            } else null

            val body = toJson(
                CreateFilterBody(room = roomFilter)
            )
            HttpRequest()
                .url("${uri}/_matrix/client/v3/user/${userId}/filter")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .json(body)
                .post()
        }
    }

    override fun createFilterBlocking(
        request: FilterCreateRequest
    ): Response<FilterCreateResponse> {
        return toBlocking { createFilter(request) }
    }

    override suspend fun getFilter(
        request: FilterGetRequest
    ): Response<FilterGetResponse> {
        return proceed {
            val userId = request.userId ?: ""
            val filterId = request.filterId ?: ""
            HttpRequest()
                .url("${uri}/_matrix/client/v3/user/${userId}/filter/${filterId}")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .get()
        }
    }

    override fun getFilterBlocking(
        request: FilterGetRequest
    ): Response<FilterGetResponse> {
        return toBlocking { getFilter(request) }
    }

    @Serializable
    private data class CreateFilterBody(
        @SerialName("room")
        val room: CreateFilterRoomFilter? = null,
    )

    @Serializable
    private data class CreateFilterRoomFilter(
        @SerialName("rooms")
        val rooms: Array<String>? = null,
        @SerialName("not_rooms")
        val notRooms: Array<String>? = null,
        @SerialName("include_leave")
        val includeLeave: Boolean? = null,
        @SerialName("timeline")
        val timeline: CreateFilterRoomEventFilter? = null,
    )

    @Serializable
    private data class CreateFilterRoomEventFilter(
        @SerialName("types")
        val types: Array<String>? = null,
        @SerialName("not_types")
        val notTypes: Array<String>? = null,
        @SerialName("senders")
        val senders: Array<String>? = null,
        @SerialName("not_senders")
        val notSenders: Array<String>? = null,
        @SerialName("limit")
        val limit: Int? = null,
    )
}
