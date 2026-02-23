package work.socialhub.kmatrix.api.request.userdirectory

import kotlin.js.JsExport

@JsExport
class UserDirectorySearchRequest {
    /** The term to search for. */
    var searchTerm: String? = null

    /** The maximum number of results to return. */
    var limit: Int? = null
}
