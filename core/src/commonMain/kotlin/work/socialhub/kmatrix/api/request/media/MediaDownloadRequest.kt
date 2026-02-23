package work.socialhub.kmatrix.api.request.media

import kotlin.js.JsExport

@JsExport
class MediaDownloadRequest {
    /** The server name from the mxc:// URI. */
    var serverName: String? = null

    /** The media ID from the mxc:// URI. */
    var mediaId: String? = null

    /** Whether to allow remote media downloads. */
    var allowRemote: Boolean? = null
}
