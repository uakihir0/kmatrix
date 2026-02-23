package work.socialhub.kmatrix.internal

import work.socialhub.khttpclient.HttpRequest
import work.socialhub.kmatrix.api.NotificationsResource
import work.socialhub.kmatrix.api.request.notifications.NotificationsGetRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.notifications.NotificationsGetResponse
import work.socialhub.kmatrix.util.Headers.AUTHORIZATION
import work.socialhub.kmatrix.util.MediaType
import work.socialhub.kmatrix.util.toBlocking

class NotificationsResourceImpl(
    uri: String,
    accessToken: String,
) : AbstractAuthResourceImpl(uri, accessToken),
    NotificationsResource {

    override suspend fun getNotifications(
        request: NotificationsGetRequest
    ): Response<NotificationsGetResponse> {
        return proceed {
            HttpRequest()
                .url("${uri}/_matrix/client/v3/notifications")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .qwn("from", request.from)
                .qwn("limit", request.limit)
                .qwn("only", request.only)
                .get()
        }
    }

    override fun getNotificationsBlocking(
        request: NotificationsGetRequest
    ): Response<NotificationsGetResponse> {
        return toBlocking { getNotifications(request) }
    }
}
