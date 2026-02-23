package work.socialhub.kmatrix.api.request.directory

import kotlin.js.JsExport

@JsExport
class DirectoryGetPublicRoomsRequest {
    /** Maximum number of results to return. */
    var limit: Int? = null

    /** A pagination token from a previous request. */
    var since: String? = null

    /** The server to fetch the public room lists from. */
    var server: String? = null
}
