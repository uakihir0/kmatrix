package work.socialhub.kmatrix.internal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import work.socialhub.khttpclient.HttpRequest
import work.socialhub.kmatrix.api.ProfileResource
import work.socialhub.kmatrix.api.request.profile.ProfileSetAvatarUrlRequest
import work.socialhub.kmatrix.api.request.profile.ProfileSetDisplayNameRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.ResponseUnit
import work.socialhub.kmatrix.api.response.profile.ProfileGetAvatarUrlResponse
import work.socialhub.kmatrix.api.response.profile.ProfileGetDisplayNameResponse
import work.socialhub.kmatrix.api.response.profile.ProfileGetProfileResponse
import work.socialhub.kmatrix.internal.InternalUtility.toJson
import work.socialhub.kmatrix.util.Headers.AUTHORIZATION
import work.socialhub.kmatrix.util.MediaType
import work.socialhub.kmatrix.util.toBlocking

class ProfileResourceImpl(
    uri: String,
    accessToken: String,
) : AbstractAuthResourceImpl(uri, accessToken),
    ProfileResource {

    override suspend fun getProfile(
        userId: String
    ): Response<ProfileGetProfileResponse> {
        return proceed {
            HttpRequest()
                .url("${uri}/_matrix/client/v3/profile/${userId}")
                .accept(MediaType.JSON)
                .get()
        }
    }

    override fun getProfileBlocking(
        userId: String
    ): Response<ProfileGetProfileResponse> {
        return toBlocking { getProfile(userId) }
    }

    override suspend fun getDisplayName(
        userId: String
    ): Response<ProfileGetDisplayNameResponse> {
        return proceed {
            HttpRequest()
                .url("${uri}/_matrix/client/v3/profile/${userId}/displayname")
                .accept(MediaType.JSON)
                .get()
        }
    }

    override fun getDisplayNameBlocking(
        userId: String
    ): Response<ProfileGetDisplayNameResponse> {
        return toBlocking { getDisplayName(userId) }
    }

    override suspend fun setDisplayName(
        request: ProfileSetDisplayNameRequest
    ): ResponseUnit {
        return proceedUnit {
            val userId = request.userId ?: ""
            val body = toJson(SetDisplayNameBody(
                displayname = request.displayname,
            ))
            HttpRequest()
                .url("${uri}/_matrix/client/v3/profile/${userId}/displayname")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .json(body)
                .put()
        }
    }

    override fun setDisplayNameBlocking(
        request: ProfileSetDisplayNameRequest
    ): ResponseUnit {
        return toBlocking { setDisplayName(request) }
    }

    override suspend fun getAvatarUrl(
        userId: String
    ): Response<ProfileGetAvatarUrlResponse> {
        return proceed {
            HttpRequest()
                .url("${uri}/_matrix/client/v3/profile/${userId}/avatar_url")
                .accept(MediaType.JSON)
                .get()
        }
    }

    override fun getAvatarUrlBlocking(
        userId: String
    ): Response<ProfileGetAvatarUrlResponse> {
        return toBlocking { getAvatarUrl(userId) }
    }

    override suspend fun setAvatarUrl(
        request: ProfileSetAvatarUrlRequest
    ): ResponseUnit {
        return proceedUnit {
            val userId = request.userId ?: ""
            val body = toJson(SetAvatarUrlBody(
                avatarUrl = request.avatarUrl,
            ))
            HttpRequest()
                .url("${uri}/_matrix/client/v3/profile/${userId}/avatar_url")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .json(body)
                .put()
        }
    }

    override fun setAvatarUrlBlocking(
        request: ProfileSetAvatarUrlRequest
    ): ResponseUnit {
        return toBlocking { setAvatarUrl(request) }
    }

    @Serializable
    private data class SetDisplayNameBody(
        @SerialName("displayname")
        val displayname: String? = null,
    )

    @Serializable
    private data class SetAvatarUrlBody(
        @SerialName("avatar_url")
        val avatarUrl: String? = null,
    )
}
