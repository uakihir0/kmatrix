package work.socialhub.kmatrix.internal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import work.socialhub.khttpclient.HttpRequest
import work.socialhub.kmatrix.api.PresenceResource
import work.socialhub.kmatrix.api.request.presence.PresenceSetRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.ResponseUnit
import work.socialhub.kmatrix.api.response.presence.PresenceGetResponse
import work.socialhub.kmatrix.internal.InternalUtility.toJson
import work.socialhub.kmatrix.util.Headers.AUTHORIZATION
import work.socialhub.kmatrix.util.MediaType
import work.socialhub.kmatrix.util.toBlocking

class PresenceResourceImpl(
    uri: String,
    accessToken: String,
) : AbstractAuthResourceImpl(uri, accessToken),
    PresenceResource {

    override suspend fun getPresence(
        userId: String
    ): Response<PresenceGetResponse> {
        return proceed {
            HttpRequest()
                .url("${uri}/_matrix/client/v3/presence/${userId}/status")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .get()
        }
    }

    override fun getPresenceBlocking(
        userId: String
    ): Response<PresenceGetResponse> {
        return toBlocking { getPresence(userId) }
    }

    override suspend fun setPresence(
        request: PresenceSetRequest
    ): ResponseUnit {
        return proceedUnit {
            val userId = request.userId ?: ""
            val body = toJson(
                SetPresenceBody(
                    presence = request.presence ?: "online",
                    statusMsg = request.statusMsg,
                )
            )
            HttpRequest()
                .url("${uri}/_matrix/client/v3/presence/${userId}/status")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .json(body)
                .put()
        }
    }

    override fun setPresenceBlocking(
        request: PresenceSetRequest
    ): ResponseUnit {
        return toBlocking { setPresence(request) }
    }

    @Serializable
    private data class SetPresenceBody(
        @SerialName("presence")
        val presence: String,
        @SerialName("status_msg")
        val statusMsg: String? = null,
    )
}
