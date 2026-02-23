package work.socialhub.kmatrix.api

import work.socialhub.kmatrix.api.request.devices.DevicesDeleteRequest
import work.socialhub.kmatrix.api.request.devices.DevicesUpdateRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.ResponseUnit
import work.socialhub.kmatrix.api.response.devices.DevicesGetDeviceResponse
import work.socialhub.kmatrix.api.response.devices.DevicesGetResponse
import kotlin.js.JsExport

@JsExport
interface DevicesResource {

    /**
     * GET /_matrix/client/v3/devices
     * Gets information about all devices for the current user.
     */
    suspend fun getDevices(): Response<DevicesGetResponse>

    @JsExport.Ignore
    fun getDevicesBlocking(): Response<DevicesGetResponse>

    /**
     * GET /_matrix/client/v3/devices/{deviceId}
     * Gets information on a single device by device id.
     */
    suspend fun getDevice(deviceId: String): Response<DevicesGetDeviceResponse>

    @JsExport.Ignore
    fun getDeviceBlocking(deviceId: String): Response<DevicesGetDeviceResponse>

    /**
     * PUT /_matrix/client/v3/devices/{deviceId}
     * Updates the metadata on the given device.
     */
    suspend fun updateDevice(request: DevicesUpdateRequest): ResponseUnit

    @JsExport.Ignore
    fun updateDeviceBlocking(request: DevicesUpdateRequest): ResponseUnit

    /**
     * DELETE /_matrix/client/v3/devices/{deviceId}
     * Deletes the given device and invalidates its access token.
     */
    suspend fun deleteDevice(request: DevicesDeleteRequest): ResponseUnit

    @JsExport.Ignore
    fun deleteDeviceBlocking(request: DevicesDeleteRequest): ResponseUnit
}
