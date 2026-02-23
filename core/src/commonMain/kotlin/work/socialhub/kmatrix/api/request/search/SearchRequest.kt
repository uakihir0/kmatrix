package work.socialhub.kmatrix.api.request.search

import kotlin.js.JsExport

@JsExport
class SearchRequest {
    /** The search term to search for. */
    var searchTerm: String? = null

    /** The keys to search (e.g., "content.body", "content.name", "content.topic"). */
    var keys: Array<String>? = null

    /** The order to list results in. One of: "recent", "rank". */
    var orderBy: String? = null

    /** Limit the results to the given room IDs. */
    var roomIds: Array<String>? = null

    /** A pagination token from a previous search. */
    var nextBatch: String? = null
}
