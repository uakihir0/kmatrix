package work.socialhub.kmatrix.internal

import work.socialhub.khttpclient.HttpRequest
import work.socialhub.kmatrix.api.VoIPResource
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.voip.VoIPGetTurnServerResponse
import work.socialhub.kmatrix.util.Headers.AUTHORIZATION
import work.socialhub.kmatrix.util.MediaType
import work.socialhub.kmatrix.util.toBlocking

class VoIPResourceImpl(
    uri: String,
    accessToken: String,
) : AbstractAuthResourceImpl(uri, accessToken),
    VoIPResource {

    override suspend fun getTurnServer(): Response<VoIPGetTurnServerResponse> {
        return proceed {
            HttpRequest()
                .url("${uri}/_matrix/client/v3/voip/turnServer")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .get()
        }
    }

    override fun getTurnServerBlocking(): Response<VoIPGetTurnServerResponse> {
        return toBlocking { getTurnServer() }
    }
}
