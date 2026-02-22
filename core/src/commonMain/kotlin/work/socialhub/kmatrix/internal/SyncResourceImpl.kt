package work.socialhub.kmatrix.internal

import work.socialhub.khttpclient.HttpRequest
import work.socialhub.kmatrix.api.SyncResource
import work.socialhub.kmatrix.api.request.sync.SyncRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.sync.SyncResponse
import work.socialhub.kmatrix.util.Headers.AUTHORIZATION
import work.socialhub.kmatrix.util.MediaType
import work.socialhub.kmatrix.util.toBlocking

class SyncResourceImpl(
    uri: String,
    accessToken: String,
) : AbstractAuthResourceImpl(uri, accessToken),
    SyncResource {

    override suspend fun sync(
        request: SyncRequest
    ): Response<SyncResponse> {
        return proceed {
            HttpRequest()
                .url("${uri}/_matrix/client/v3/sync")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .qwn("since", request.since)
                .qwn("timeout", request.timeout)
                .qwn("filter", request.filter)
                .qwn("full_state", request.fullState)
                .qwn("set_presence", request.setPresence)
                .get()
        }
    }

    override fun syncBlocking(
        request: SyncRequest
    ): Response<SyncResponse> {
        return toBlocking { sync(request) }
    }
}
