package work.socialhub.kmatrix.internal

import work.socialhub.khttpclient.HttpRequest
import work.socialhub.kmatrix.api.RelationsResource
import work.socialhub.kmatrix.api.request.relations.RelationsGetRequest
import work.socialhub.kmatrix.api.request.relations.ThreadsGetRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.relations.RelationsGetResponse
import work.socialhub.kmatrix.api.response.relations.ThreadsGetResponse
import work.socialhub.kmatrix.util.Headers.AUTHORIZATION
import work.socialhub.kmatrix.util.MediaType
import work.socialhub.kmatrix.util.toBlocking

class RelationsResourceImpl(
    uri: String,
    accessToken: String,
) : AbstractAuthResourceImpl(uri, accessToken),
    RelationsResource {

    override suspend fun getRelations(
        request: RelationsGetRequest
    ): Response<RelationsGetResponse> {
        return proceed {
            val url = buildString {
                append("${uri}/_matrix/client/v1/rooms/${request.roomId}/relations/${request.eventId}")
                if (request.relType != null) {
                    append("/${request.relType}")
                    if (request.eventType != null) {
                        append("/${request.eventType}")
                    }
                }
            }
            HttpRequest()
                .url(url)
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .qwn("from", request.from)
                .qwn("to", request.to)
                .qwn("limit", request.limit)
                .qwn("dir", request.dir)
                .get()
        }
    }

    override fun getRelationsBlocking(
        request: RelationsGetRequest
    ): Response<RelationsGetResponse> {
        return toBlocking { getRelations(request) }
    }

    override suspend fun getThreads(
        request: ThreadsGetRequest
    ): Response<ThreadsGetResponse> {
        return proceed {
            HttpRequest()
                .url("${uri}/_matrix/client/v1/rooms/${request.roomId}/threads")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .qwn("include", request.include)
                .qwn("limit", request.limit)
                .qwn("from", request.from)
                .get()
        }
    }

    override fun getThreadsBlocking(
        request: ThreadsGetRequest
    ): Response<ThreadsGetResponse> {
        return toBlocking { getThreads(request) }
    }
}
