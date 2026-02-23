package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import kotlin.test.Test

class CapabilitiesTest : AbstractTest() {

    @Test
    fun testGetCapabilities() = runTest {
        val response = matrix().capabilities().getCapabilities()
        println("=== Capabilities ===")
        val caps = response.data.capabilities
        println("  Change Password > ${caps.changePassword?.enabled}")
        println("  Set Displayname > ${caps.setDisplayname?.enabled}")
        println("  Set Avatar URL  > ${caps.setAvatarUrl?.enabled}")
        caps.roomVersions?.let {
            println("  Default Room Version > ${it.default}")
            it.available.forEach { (version, stability) ->
                println("    Room $version > $stability")
            }
        }
    }
}
