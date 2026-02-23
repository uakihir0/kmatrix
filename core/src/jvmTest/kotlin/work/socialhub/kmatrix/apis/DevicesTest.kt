package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import kotlin.test.Test

class DevicesTest : AbstractTest() {

    @Test
    fun testGetDevices() = runTest {
        val response = matrix().devices().getDevices()
        println("=== Devices ===")
        for (device in response.data.devices) {
            println("  Device ID    > ${device.deviceId}")
            println("  Display Name > ${device.displayName}")
            println("  Last Seen IP > ${device.lastSeenIp}")
            println("  Last Seen TS > ${device.lastSeenTs}")
            println("  ---")
        }
    }

    @Test
    fun testGetDevice() = runTest {
        val devices = matrix().devices().getDevices()
        if (devices.data.devices.isNotEmpty()) {
            val deviceId = devices.data.devices.first().deviceId
            val response = matrix().devices().getDevice(deviceId)
            println("=== Device ===")
            println("  Device ID    > ${response.data.deviceId}")
            println("  Display Name > ${response.data.displayName}")
            println("  Last Seen IP > ${response.data.lastSeenIp}")
            println("  Last Seen TS > ${response.data.lastSeenTs}")
        }
    }
}
