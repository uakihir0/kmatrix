package work.socialhub.kmatrix.internal

import io.ktor.http.encodeURLParameter
import work.socialhub.khttpclient.HttpRequest
import work.socialhub.kmatrix.MatrixException
import work.socialhub.kmatrix.api.MediaResource
import work.socialhub.kmatrix.api.request.media.MediaDownloadRequest
import work.socialhub.kmatrix.api.request.media.MediaPreviewUrlRequest
import work.socialhub.kmatrix.api.request.media.MediaThumbnailRequest
import work.socialhub.kmatrix.api.request.media.MediaUploadRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.media.MediaGetConfigResponse
import work.socialhub.kmatrix.api.response.media.MediaPreviewUrlResponse
import work.socialhub.kmatrix.api.response.media.MediaUploadResponse
import work.socialhub.kmatrix.util.Headers.AUTHORIZATION
import work.socialhub.kmatrix.util.MediaType
import work.socialhub.kmatrix.util.toBlocking

class MediaResourceImpl(
    uri: String,
    accessToken: String,
) : AbstractAuthResourceImpl(uri, accessToken),
    MediaResource {

    override suspend fun upload(
        request: MediaUploadRequest
    ): Response<MediaUploadResponse> {
        return proceed {
            val url = buildString {
                append("${uri}/_matrix/media/v3/upload")
                request.fileName?.let {
                    append("?filename=${it.encodeURLParameter()}")
                }
            }
            HttpRequest()
                .url(url)
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .file("file", request.fileName ?: "upload", request.bytes ?: byteArrayOf())
                .post()
        }
    }

    override fun uploadBlocking(
        request: MediaUploadRequest
    ): Response<MediaUploadResponse> {
        return toBlocking { upload(request) }
    }

    override suspend fun download(
        request: MediaDownloadRequest
    ): ByteArray {
        try {
            val serverName = request.serverName ?: ""
            val mediaId = request.mediaId ?: ""
            val response = HttpRequest()
                .url("${uri}/_matrix/media/v3/download/${serverName}/${mediaId}")
                .header(AUTHORIZATION, bearerToken())
                .qwn("allow_remote", request.allowRemote)
                .get()
            if (response.status == 200) {
                return response.body
            }
            throw MatrixException(response.status, response.stringBody)
        } catch (e: Exception) {
            throw e as? MatrixException ?: MatrixException(e)
        }
    }

    override fun downloadBlocking(
        request: MediaDownloadRequest
    ): ByteArray {
        return toBlocking { download(request) }
    }

    override suspend fun thumbnail(
        request: MediaThumbnailRequest
    ): ByteArray {
        try {
            val serverName = request.serverName ?: ""
            val mediaId = request.mediaId ?: ""
            val response = HttpRequest()
                .url("${uri}/_matrix/media/v3/thumbnail/${serverName}/${mediaId}")
                .header(AUTHORIZATION, bearerToken())
                .qwn("width", request.width)
                .qwn("height", request.height)
                .qwn("method", request.method)
                .get()
            if (response.status == 200) {
                return response.body
            }
            throw MatrixException(response.status, response.stringBody)
        } catch (e: Exception) {
            throw e as? MatrixException ?: MatrixException(e)
        }
    }

    override fun thumbnailBlocking(
        request: MediaThumbnailRequest
    ): ByteArray {
        return toBlocking { thumbnail(request) }
    }

    override suspend fun getConfig(): Response<MediaGetConfigResponse> {
        return proceed {
            HttpRequest()
                .url("${uri}/_matrix/media/v3/config")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .get()
        }
    }

    override fun getConfigBlocking(): Response<MediaGetConfigResponse> {
        return toBlocking { getConfig() }
    }

    override suspend fun previewUrl(
        request: MediaPreviewUrlRequest
    ): Response<MediaPreviewUrlResponse> {
        return proceed {
            HttpRequest()
                .url("${uri}/_matrix/media/v3/preview_url")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .qwn("url", request.url)
                .qwn("ts", request.ts)
                .get()
        }
    }

    override fun previewUrlBlocking(
        request: MediaPreviewUrlRequest
    ): Response<MediaPreviewUrlResponse> {
        return toBlocking { previewUrl(request) }
    }
}
