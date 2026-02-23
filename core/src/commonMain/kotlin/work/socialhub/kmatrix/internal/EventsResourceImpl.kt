package work.socialhub.kmatrix.internal

import work.socialhub.khttpclient.HttpRequest
import work.socialhub.kmatrix.api.EventsResource
import work.socialhub.kmatrix.api.request.events.EventsGetContextRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.events.EventsGetContextResponse
import work.socialhub.kmatrix.api.response.events.EventsGetEventResponse
import work.socialhub.kmatrix.util.Headers.AUTHORIZATION
import work.socialhub.kmatrix.util.MediaType
import work.socialhub.kmatrix.util.toBlocking

class EventsResourceImpl(
    uri: String,
    accessToken: String,
) : AbstractAuthResourceImpl(uri, accessToken),
    EventsResource {

    override suspend fun getEvent(
        roomId: String,
        eventId: String
    ): Response<EventsGetEventResponse> {
        return proceed {
            HttpRequest()
                .url("${uri}/_matrix/client/v3/rooms/${roomId}/event/${eventId}")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .get()
        }
    }

    override fun getEventBlocking(
        roomId: String,
        eventId: String
    ): Response<EventsGetEventResponse> {
        return toBlocking { getEvent(roomId, eventId) }
    }

    override suspend fun getContext(
        request: EventsGetContextRequest
    ): Response<EventsGetContextResponse> {
        return proceed {
            HttpRequest()
                .url("${uri}/_matrix/client/v3/rooms/${request.roomId}/context/${request.eventId}")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .qwn("limit", request.limit)
                .qwn("filter", request.filter)
                .get()
        }
    }

    override fun getContextBlocking(
        request: EventsGetContextRequest
    ): Response<EventsGetContextResponse> {
        return toBlocking { getContext(request) }
    }
}
