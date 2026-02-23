package work.socialhub.kmatrix.internal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import work.socialhub.khttpclient.HttpRequest
import work.socialhub.kmatrix.api.TagsResource
import work.socialhub.kmatrix.api.request.tags.TagsDeleteRequest
import work.socialhub.kmatrix.api.request.tags.TagsSetRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.ResponseUnit
import work.socialhub.kmatrix.api.response.tags.TagsGetResponse
import work.socialhub.kmatrix.internal.InternalUtility.toJson
import work.socialhub.kmatrix.util.Headers.AUTHORIZATION
import work.socialhub.kmatrix.util.MediaType
import work.socialhub.kmatrix.util.toBlocking

class TagsResourceImpl(
    uri: String,
    accessToken: String,
) : AbstractAuthResourceImpl(uri, accessToken),
    TagsResource {

    override suspend fun getTags(
        userId: String,
        roomId: String
    ): Response<TagsGetResponse> {
        return proceed {
            HttpRequest()
                .url("${uri}/_matrix/client/v3/user/${userId}/rooms/${roomId}/tags")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .get()
        }
    }

    override fun getTagsBlocking(
        userId: String,
        roomId: String
    ): Response<TagsGetResponse> {
        return toBlocking { getTags(userId, roomId) }
    }

    override suspend fun setTag(
        request: TagsSetRequest
    ): ResponseUnit {
        return proceedUnit {
            val userId = request.userId ?: ""
            val roomId = request.roomId ?: ""
            val tag = request.tag ?: ""
            val body = toJson(
                SetTagBody(order = request.order)
            )
            HttpRequest()
                .url("${uri}/_matrix/client/v3/user/${userId}/rooms/${roomId}/tags/${tag}")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .json(body)
                .put()
        }
    }

    override fun setTagBlocking(
        request: TagsSetRequest
    ): ResponseUnit {
        return toBlocking { setTag(request) }
    }

    override suspend fun deleteTag(
        request: TagsDeleteRequest
    ): ResponseUnit {
        return proceedUnit {
            val userId = request.userId ?: ""
            val roomId = request.roomId ?: ""
            val tag = request.tag ?: ""
            HttpRequest()
                .url("${uri}/_matrix/client/v3/user/${userId}/rooms/${roomId}/tags/${tag}")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .delete()
        }
    }

    override fun deleteTagBlocking(
        request: TagsDeleteRequest
    ): ResponseUnit {
        return toBlocking { deleteTag(request) }
    }

    @Serializable
    private data class SetTagBody(
        @SerialName("order")
        val order: Double? = null,
    )
}
