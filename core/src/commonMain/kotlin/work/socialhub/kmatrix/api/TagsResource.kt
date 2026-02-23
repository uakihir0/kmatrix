package work.socialhub.kmatrix.api

import work.socialhub.kmatrix.api.request.tags.TagsDeleteRequest
import work.socialhub.kmatrix.api.request.tags.TagsSetRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.ResponseUnit
import work.socialhub.kmatrix.api.response.tags.TagsGetResponse
import kotlin.js.JsExport

@JsExport
interface TagsResource {

    /**
     * GET /_matrix/client/v3/user/{userId}/rooms/{roomId}/tags
     * List the tags set on a room by a user.
     */
    suspend fun getTags(userId: String, roomId: String): Response<TagsGetResponse>

    @JsExport.Ignore
    fun getTagsBlocking(userId: String, roomId: String): Response<TagsGetResponse>

    /**
     * PUT /_matrix/client/v3/user/{userId}/rooms/{roomId}/tags/{tag}
     * Add a tag to a room.
     */
    suspend fun setTag(request: TagsSetRequest): ResponseUnit

    @JsExport.Ignore
    fun setTagBlocking(request: TagsSetRequest): ResponseUnit

    /**
     * DELETE /_matrix/client/v3/user/{userId}/rooms/{roomId}/tags/{tag}
     * Remove a tag from a room.
     */
    suspend fun deleteTag(request: TagsDeleteRequest): ResponseUnit

    @JsExport.Ignore
    fun deleteTagBlocking(request: TagsDeleteRequest): ResponseUnit
}
