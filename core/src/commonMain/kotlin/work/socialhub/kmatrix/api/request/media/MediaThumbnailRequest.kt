package work.socialhub.kmatrix.api.request.media

import kotlin.js.JsExport

@JsExport
class MediaThumbnailRequest {
    /** The server name from the mxc:// URI. */
    var serverName: String? = null

    /** The media ID from the mxc:// URI. */
    var mediaId: String? = null

    /** The desired width of the thumbnail in pixels. */
    var width: Int? = null

    /** The desired height of the thumbnail in pixels. */
    var height: Int? = null

    /** The desired resizing method. One of: "crop", "scale". */
    var method: String? = null
}
