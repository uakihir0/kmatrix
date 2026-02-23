package work.socialhub.kmatrix.api

import work.socialhub.kmatrix.api.request.events.EventsGetContextRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.events.EventsGetContextResponse
import work.socialhub.kmatrix.api.response.events.EventsGetEventResponse
import kotlin.js.JsExport

@JsExport
interface EventsResource {

    /**
     * GET /_matrix/client/v3/rooms/{roomId}/event/{eventId}
     * Get a single event based on roomId/eventId.
     */
    suspend fun getEvent(
        roomId: String,
        eventId: String
    ): Response<EventsGetEventResponse>

    @JsExport.Ignore
    fun getEventBlocking(
        roomId: String,
        eventId: String
    ): Response<EventsGetEventResponse>

    /**
     * GET /_matrix/client/v3/rooms/{roomId}/context/{eventId}
     * Get a number of events that happened just before and after
     * the specified event.
     */
    suspend fun getContext(
        request: EventsGetContextRequest
    ): Response<EventsGetContextResponse>

    @JsExport.Ignore
    fun getContextBlocking(
        request: EventsGetContextRequest
    ): Response<EventsGetContextResponse>
}
