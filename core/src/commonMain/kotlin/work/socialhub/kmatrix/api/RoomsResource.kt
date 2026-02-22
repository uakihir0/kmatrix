package work.socialhub.kmatrix.api

import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.rooms.RoomsGetJoinedRoomsResponse
import kotlin.js.JsExport

@JsExport
interface RoomsResource {

    suspend fun getJoinedRooms(): Response<RoomsGetJoinedRoomsResponse>

    @JsExport.Ignore
    fun getJoinedRoomsBlocking(): Response<RoomsGetJoinedRoomsResponse>
}
