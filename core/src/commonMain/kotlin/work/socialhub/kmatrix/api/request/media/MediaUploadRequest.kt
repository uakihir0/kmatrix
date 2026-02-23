package work.socialhub.kmatrix.api.request.media

import kotlin.js.JsExport

@JsExport
class MediaUploadRequest {
    /** The file name to give to the media. */
    var fileName: String? = null

    /** The content type of the file (e.g., "image/png"). */
    var contentType: String? = null

    /** The raw bytes of the file to upload. */
    var bytes: ByteArray? = null
}
