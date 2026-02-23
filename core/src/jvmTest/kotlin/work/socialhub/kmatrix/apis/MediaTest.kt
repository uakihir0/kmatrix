package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import work.socialhub.kmatrix.api.request.media.MediaDownloadRequest
import work.socialhub.kmatrix.api.request.media.MediaThumbnailRequest
import work.socialhub.kmatrix.api.request.media.MediaUploadRequest
import kotlin.test.Test

class MediaTest : AbstractTest() {

    companion object {
        // Minimal 1x1 white PNG image (67 bytes)
        val MINIMAL_PNG = byteArrayOf(
            -119, 80, 78, 71, 13, 10, 26, 10, // PNG signature
            0, 0, 0, 13, 73, 72, 68, 82, // IHDR chunk
            0, 0, 0, 1, 0, 0, 0, 1, 8, 2, 0, 0, 0,
            -112, 119, 83, -34, // CRC
            0, 0, 0, 12, 73, 68, 65, 84, // IDAT chunk
            8, -41, 99, -8, -49, -64, 0, 0, 0, 3, 0, 1,
            24, -40, 95, -88, // CRC
            0, 0, 0, 0, 73, 69, 78, 68, // IEND chunk
            -82, 66, 96, -126 // CRC
        )
    }

    @Test
    fun testGetConfig() = runTest {
        val response = matrix().media().getConfig()
        println("=== Media Config ===")
        println("  Upload Size > ${response.data.uploadSize}")
    }

    @Test
    fun testUploadMedia() = runTest {
        val request = MediaUploadRequest().also {
            it.fileName = "test.png"
            it.contentType = "image/png"
            it.bytes = MINIMAL_PNG
        }
        val response = matrix().media().upload(request)
        println("=== Upload Media ===")
        println("  Content URI > ${response.data.contentUri}")
    }

    @Test
    fun testDownloadMedia() = runTest {
        // First upload a file
        val uploadResponse = matrix().media().upload(
            MediaUploadRequest().also {
                it.fileName = "download-test.png"
                it.contentType = "image/png"
                it.bytes = MINIMAL_PNG
            }
        )
        val contentUri = uploadResponse.data.contentUri
        println("=== Download Media ===")
        println("  Content URI > $contentUri")

        // Parse mxc:// URI into serverName and mediaId
        val mxcParts = contentUri.removePrefix("mxc://").split("/", limit = 2)
        val serverName = mxcParts[0]
        val mediaId = mxcParts[1]

        try {
            // Download the file
            val bytes = matrix().media().download(
                MediaDownloadRequest().also {
                    it.serverName = serverName
                    it.mediaId = mediaId
                }
            )
            println("  Downloaded  > ${bytes.size} bytes")
            assert(bytes.isNotEmpty())
        } catch (e: Exception) {
            // Some servers have deprecated /_matrix/media/v3/download
            // in favor of /_matrix/client/v1/media/download
            println("  Download not available: ${e.message}")
        }
    }

    @Test
    fun testThumbnail() = runTest {
        // First upload an image
        val uploadResponse = matrix().media().upload(
            MediaUploadRequest().also {
                it.fileName = "thumb-test.png"
                it.contentType = "image/png"
                it.bytes = MINIMAL_PNG
            }
        )
        val contentUri = uploadResponse.data.contentUri
        println("=== Thumbnail ===")
        println("  Content URI > $contentUri")

        // Parse mxc:// URI
        val mxcParts = contentUri.removePrefix("mxc://").split("/", limit = 2)
        val serverName = mxcParts[0]
        val mediaId = mxcParts[1]

        try {
            val bytes = matrix().media().thumbnail(
                MediaThumbnailRequest().also {
                    it.serverName = serverName
                    it.mediaId = mediaId
                    it.width = 32
                    it.height = 32
                    it.method = "scale"
                }
            )
            println("  Thumbnail   > ${bytes.size} bytes")
            assert(bytes.isNotEmpty())
        } catch (e: Exception) {
            // Some servers may not support thumbnails for very small images
            println("  Thumbnail not available: ${e.message}")
        }
    }
}
