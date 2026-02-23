package work.socialhub.kmatrix.apis

import kotlinx.coroutines.test.runTest
import work.socialhub.kmatrix.AbstractTest
import work.socialhub.kmatrix.api.request.devices.DevicesUpdateRequest
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

    @Test
    fun testUpdateDevice() = runTest {
        val devices = matrix().devices().getDevices()
        if (devices.data.devices.isNotEmpty()) {
            val device = devices.data.devices.first()
            val deviceId = device.deviceId
            val originalName = device.displayName

            // Update device display name
            val testName = (originalName ?: "kmatrix-device") + " (test)"
            matrix().devices().updateDevice(
                DevicesUpdateRequest().also {
                    it.deviceId = deviceId
                    it.displayName = testName
                }
            )
            println("=== Update Device ===")
            println("  Device ID     > $deviceId")
            println("  Updated Name  > $testName")

            // Verify update
            val updated = matrix().devices().getDevice(deviceId)
            println("  Verified Name > ${updated.data.displayName}")

            // Restore original name
            matrix().devices().updateDevice(
                DevicesUpdateRequest().also {
                    it.deviceId = deviceId
                    it.displayName = originalName ?: ""
                }
            )
            println("  Restored Name > $originalName")
        }
    }
}
