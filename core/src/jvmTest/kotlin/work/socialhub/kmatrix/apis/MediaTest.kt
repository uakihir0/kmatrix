package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import work.socialhub.kmatrix.api.request.media.MediaDownloadRequest
import work.socialhub.kmatrix.api.request.media.MediaUploadRequest
import kotlin.test.Test

class MediaTest : AbstractTest() {

    @Test
    fun testGetConfig() = runTest {
        val response = matrix().media().getConfig()
        println("=== Media Config ===")
        println("  Upload Size > ${response.data.uploadSize}")
    }

    @Test
    fun testUploadMedia() = runTest {
        val content = "Hello from kmatrix test".toByteArray()
        val request = MediaUploadRequest().also {
            it.fileName = "test.txt"
            it.contentType = "text/plain"
            it.bytes = content
        }
        val response = matrix().media().upload(request)
        println("=== Upload Media ===")
        println("  Content URI > ${response.data.contentUri}")
    }

    @Test
    fun testUploadAndDownloadMedia() = runTest {
        val content = "Hello from kmatrix download test".toByteArray()
        val uploadRequest = MediaUploadRequest().also {
            it.fileName = "test-download.txt"
            it.contentType = "text/plain"
            it.bytes = content
        }
        val uploadResponse = matrix().media().upload(uploadRequest)
        val contentUri = uploadResponse.data.contentUri
        println("=== Uploaded Media ===")
        println("  Content URI > $contentUri")

        // Parse mxc://server/mediaId
        val mxcParts = contentUri.removePrefix("mxc://").split("/", limit = 2)
        val downloadRequest = MediaDownloadRequest().also {
            it.serverName = mxcParts[0]
            it.mediaId = mxcParts[1]
        }
        val downloaded = matrix().media().download(downloadRequest)
        println("=== Downloaded Media ===")
        println("  Content > ${downloaded.decodeToString()}")
        println("  Size    > ${downloaded.size} bytes")
    }
}
