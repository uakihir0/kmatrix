package work.socialhub.kmatrix.api

import work.socialhub.kmatrix.api.request.notifications.NotificationsGetRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.notifications.NotificationsGetResponse
import kotlin.js.JsExport

@JsExport
interface NotificationsResource {

    /**
     * GET /_matrix/client/v3/notifications
     * Gets a list of events that the user has been notified about.
     */
    suspend fun getNotifications(
        request: NotificationsGetRequest
    ): Response<NotificationsGetResponse>

    @JsExport.Ignore
    fun getNotificationsBlocking(
        request: NotificationsGetRequest
    ): Response<NotificationsGetResponse>
}
