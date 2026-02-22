package work.socialhub.kmatrix.api

import work.socialhub.kmatrix.api.request.profile.ProfileSetDisplayNameRequest
import work.socialhub.kmatrix.api.request.profile.ProfileSetAvatarUrlRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.ResponseUnit
import work.socialhub.kmatrix.api.response.profile.ProfileGetProfileResponse
import work.socialhub.kmatrix.api.response.profile.ProfileGetDisplayNameResponse
import work.socialhub.kmatrix.api.response.profile.ProfileGetAvatarUrlResponse
import kotlin.js.JsExport

@JsExport
interface ProfileResource {

    /**
     * GET /_matrix/client/v3/profile/{userId}
     * Get the combined profile information for this user.
     */
    suspend fun getProfile(userId: String): Response<ProfileGetProfileResponse>

    @JsExport.Ignore
    fun getProfileBlocking(userId: String): Response<ProfileGetProfileResponse>

    /**
     * GET /_matrix/client/v3/profile/{userId}/displayname
     * Get the user's display name.
     */
    suspend fun getDisplayName(userId: String): Response<ProfileGetDisplayNameResponse>

    @JsExport.Ignore
    fun getDisplayNameBlocking(userId: String): Response<ProfileGetDisplayNameResponse>

    /**
     * PUT /_matrix/client/v3/profile/{userId}/displayname
     * Set the user's display name.
     */
    suspend fun setDisplayName(request: ProfileSetDisplayNameRequest): ResponseUnit

    @JsExport.Ignore
    fun setDisplayNameBlocking(request: ProfileSetDisplayNameRequest): ResponseUnit

    /**
     * GET /_matrix/client/v3/profile/{userId}/avatar_url
     * Get the user's avatar URL.
     */
    suspend fun getAvatarUrl(userId: String): Response<ProfileGetAvatarUrlResponse>

    @JsExport.Ignore
    fun getAvatarUrlBlocking(userId: String): Response<ProfileGetAvatarUrlResponse>

    /**
     * PUT /_matrix/client/v3/profile/{userId}/avatar_url
     * Set the user's avatar URL.
     */
    suspend fun setAvatarUrl(request: ProfileSetAvatarUrlRequest): ResponseUnit

    @JsExport.Ignore
    fun setAvatarUrlBlocking(request: ProfileSetAvatarUrlRequest): ResponseUnit
}
