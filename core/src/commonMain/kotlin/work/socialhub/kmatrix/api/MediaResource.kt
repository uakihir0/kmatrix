package work.socialhub.kmatrix.api

import work.socialhub.kmatrix.api.request.media.MediaDownloadRequest
import work.socialhub.kmatrix.api.request.media.MediaPreviewUrlRequest
import work.socialhub.kmatrix.api.request.media.MediaThumbnailRequest
import work.socialhub.kmatrix.api.request.media.MediaUploadRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.media.MediaGetConfigResponse
import work.socialhub.kmatrix.api.response.media.MediaPreviewUrlResponse
import work.socialhub.kmatrix.api.response.media.MediaUploadResponse
import kotlin.js.JsExport

@JsExport
interface MediaResource {

    /**
     * POST /_matrix/media/v3/upload
     * Upload some content to the content repository.
     */
    suspend fun upload(
        request: MediaUploadRequest
    ): Response<MediaUploadResponse>

    @JsExport.Ignore
    fun uploadBlocking(
        request: MediaUploadRequest
    ): Response<MediaUploadResponse>

    /**
     * GET /_matrix/media/v3/download/{serverName}/{mediaId}
     * Download content from the content repository.
     */
    suspend fun download(
        request: MediaDownloadRequest
    ): ByteArray

    @JsExport.Ignore
    fun downloadBlocking(
        request: MediaDownloadRequest
    ): ByteArray

    /**
     * GET /_matrix/media/v3/thumbnail/{serverName}/{mediaId}
     * Download a thumbnail of content from the content repository.
     */
    suspend fun thumbnail(
        request: MediaThumbnailRequest
    ): ByteArray

    @JsExport.Ignore
    fun thumbnailBlocking(
        request: MediaThumbnailRequest
    ): ByteArray

    /**
     * GET /_matrix/media/v3/config
     * Get the media repository configuration.
     */
    suspend fun getConfig(): Response<MediaGetConfigResponse>

    @JsExport.Ignore
    fun getConfigBlocking(): Response<MediaGetConfigResponse>

    /**
     * GET /_matrix/media/v3/preview_url
     * Get information about a URL for the client.
     * This includes OpenGraph data and an optional image.
     */
    suspend fun previewUrl(
        request: MediaPreviewUrlRequest
    ): Response<MediaPreviewUrlResponse>

    @JsExport.Ignore
    fun previewUrlBlocking(
        request: MediaPreviewUrlRequest
    ): Response<MediaPreviewUrlResponse>
}
