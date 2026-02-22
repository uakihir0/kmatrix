package work.socialhub.kmatrix.internal

import work.socialhub.khttpclient.HttpRequest
import work.socialhub.kmatrix.api.RoomsResource
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.rooms.RoomsGetJoinedRoomsResponse
import work.socialhub.kmatrix.util.Headers.AUTHORIZATION
import work.socialhub.kmatrix.util.MediaType
import work.socialhub.kmatrix.util.toBlocking

class RoomsResourceImpl(
    uri: String,
    accessToken: String,
) : AbstractAuthResourceImpl(uri, accessToken),
    RoomsResource {

    override suspend fun getJoinedRooms(): Response<RoomsGetJoinedRoomsResponse> {
        return proceed {
            HttpRequest()
                .url("${uri}/_matrix/client/v3/joined_rooms")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .get()
        }
    }

    override fun getJoinedRoomsBlocking(): Response<RoomsGetJoinedRoomsResponse> {
        return toBlocking { getJoinedRooms() }
    }
}
