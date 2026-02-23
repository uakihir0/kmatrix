package work.socialhub.kmatrix.api

import work.socialhub.kmatrix.api.request.directory.DirectoryGetPublicRoomsRequest
import work.socialhub.kmatrix.api.request.directory.DirectorySetAliasRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.ResponseUnit
import work.socialhub.kmatrix.api.response.directory.DirectoryGetPublicRoomsResponse
import work.socialhub.kmatrix.api.response.directory.DirectoryResolveAliasResponse
import kotlin.js.JsExport

@JsExport
interface DirectoryResource {

    /**
     * GET /_matrix/client/v3/directory/room/{roomAlias}
     * Resolve a room alias to a room ID.
     */
    suspend fun resolveAlias(roomAlias: String): Response<DirectoryResolveAliasResponse>

    @JsExport.Ignore
    fun resolveAliasBlocking(roomAlias: String): Response<DirectoryResolveAliasResponse>

    /**
     * PUT /_matrix/client/v3/directory/room/{roomAlias}
     * Create a new mapping from room alias to room ID.
     */
    suspend fun setAlias(request: DirectorySetAliasRequest): ResponseUnit

    @JsExport.Ignore
    fun setAliasBlocking(request: DirectorySetAliasRequest): ResponseUnit

    /**
     * DELETE /_matrix/client/v3/directory/room/{roomAlias}
     * Remove a mapping of room alias to room ID.
     */
    suspend fun deleteAlias(roomAlias: String): ResponseUnit

    @JsExport.Ignore
    fun deleteAliasBlocking(roomAlias: String): ResponseUnit

    /**
     * GET /_matrix/client/v3/publicRooms
     * Lists the public rooms on the server.
     */
    suspend fun getPublicRooms(
        request: DirectoryGetPublicRoomsRequest
    ): Response<DirectoryGetPublicRoomsResponse>

    @JsExport.Ignore
    fun getPublicRoomsBlocking(
        request: DirectoryGetPublicRoomsRequest
    ): Response<DirectoryGetPublicRoomsResponse>
}
