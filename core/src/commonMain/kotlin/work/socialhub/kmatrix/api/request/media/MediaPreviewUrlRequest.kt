package work.socialhub.kmatrix.api.request.media

import kotlin.js.JsExport

@JsExport
class MediaPreviewUrlRequest {
    /** The URL to get a preview of. */
    var url: String? = null

    /** The preferred point in time to return a preview for.
     *  The server may return a newer version if it does not have
     *  the requested version available. Timestamp in milliseconds. */
    var ts: Long? = null
}
