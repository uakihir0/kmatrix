package work.socialhub.kmatrix.internal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import work.socialhub.khttpclient.HttpRequest
import work.socialhub.kmatrix.api.PushersResource
import work.socialhub.kmatrix.api.request.pushers.PushersSetRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.ResponseUnit
import work.socialhub.kmatrix.api.response.pushers.PushersGetResponse
import work.socialhub.kmatrix.internal.InternalUtility.toJson
import work.socialhub.kmatrix.util.Headers.AUTHORIZATION
import work.socialhub.kmatrix.util.MediaType
import work.socialhub.kmatrix.util.toBlocking

class PushersResourceImpl(
    uri: String,
    accessToken: String,
) : AbstractAuthResourceImpl(uri, accessToken),
    PushersResource {

    override suspend fun getPushers(): Response<PushersGetResponse> {
        return proceed {
            HttpRequest()
                .url("${uri}/_matrix/client/v3/pushers")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .get()
        }
    }

    override fun getPushersBlocking(): Response<PushersGetResponse> {
        return toBlocking { getPushers() }
    }

    override suspend fun setPusher(request: PushersSetRequest): ResponseUnit {
        return proceedUnit {
            val body = toJson(
                SetPusherBody(
                    pushkey = request.pushkey ?: "",
                    kind = request.kind ?: "",
                    appId = request.appId ?: "",
                    appDisplayName = request.appDisplayName ?: "",
                    deviceDisplayName = request.deviceDisplayName ?: "",
                    profileTag = request.profileTag,
                    lang = request.lang ?: "",
                    data = request.data?.let {
                        SetPusherBody.PusherDataBody(
                            url = it.url,
                            format = it.format,
                        )
                    } ?: SetPusherBody.PusherDataBody(),
                    append = request.append,
                    enabled = request.enabled,
                )
            )
            HttpRequest()
                .url("${uri}/_matrix/client/v3/pushers/set")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .json(body)
                .post()
        }
    }

    override fun setPusherBlocking(request: PushersSetRequest): ResponseUnit {
        return toBlocking { setPusher(request) }
    }

    @Serializable
    private data class SetPusherBody(
        @SerialName("pushkey")
        val pushkey: String,
        @SerialName("kind")
        val kind: String,
        @SerialName("app_id")
        val appId: String,
        @SerialName("app_display_name")
        val appDisplayName: String,
        @SerialName("device_display_name")
        val deviceDisplayName: String,
        @SerialName("profile_tag")
        val profileTag: String?,
        @SerialName("lang")
        val lang: String,
        @SerialName("data")
        val data: PusherDataBody,
        @SerialName("append")
        val append: Boolean?,
        @SerialName("enabled")
        val enabled: Boolean?,
    ) {
        @Serializable
        data class PusherDataBody(
            @SerialName("url")
            val url: String? = null,
            @SerialName("format")
            val format: String? = null,
        )
    }
}
