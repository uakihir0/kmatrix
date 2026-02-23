package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import kotlin.test.Test

class VersionsTest : AbstractTest() {

    @Test
    fun testGetVersions() = runTest {
        val response = matrixNoAuth().versions().getVersions()
        println("=== Versions ===")
        for (version in response.data.versions) {
            println("  Version > $version")
        }
        response.data.unstableFeatures?.forEach { (key, value) ->
            println("  Unstable > $key = $value")
        }
        assert(response.data.versions.isNotEmpty())
    }
}
