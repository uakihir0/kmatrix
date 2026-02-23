package work.socialhub.kmatrix.api.request.directory

import kotlin.js.JsExport

@JsExport
class DirectorySetAliasRequest {
    /** The room alias to set (e.g., "#alias:example.com"). */
    var roomAlias: String? = null

    /** The room ID to set the alias for. */
    var roomId: String? = null
}
