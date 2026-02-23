package work.socialhub.kmatrix.internal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import work.socialhub.khttpclient.HttpRequest
import work.socialhub.kmatrix.api.DevicesResource
import work.socialhub.kmatrix.api.request.devices.DevicesDeleteRequest
import work.socialhub.kmatrix.api.request.devices.DevicesUpdateRequest
import work.socialhub.kmatrix.api.response.Response
import work.socialhub.kmatrix.api.response.ResponseUnit
import work.socialhub.kmatrix.api.response.devices.DevicesGetDeviceResponse
import work.socialhub.kmatrix.api.response.devices.DevicesGetResponse
import work.socialhub.kmatrix.internal.InternalUtility.toJson
import work.socialhub.kmatrix.util.Headers.AUTHORIZATION
import work.socialhub.kmatrix.util.MediaType
import work.socialhub.kmatrix.util.toBlocking

class DevicesResourceImpl(
    uri: String,
    accessToken: String,
) : AbstractAuthResourceImpl(uri, accessToken),
    DevicesResource {

    override suspend fun getDevices(): Response<DevicesGetResponse> {
        return proceed {
            HttpRequest()
                .url("${uri}/_matrix/client/v3/devices")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .get()
        }
    }

    override fun getDevicesBlocking(): Response<DevicesGetResponse> {
        return toBlocking { getDevices() }
    }

    override suspend fun getDevice(
        deviceId: String
    ): Response<DevicesGetDeviceResponse> {
        return proceed {
            HttpRequest()
                .url("${uri}/_matrix/client/v3/devices/${deviceId}")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .get()
        }
    }

    override fun getDeviceBlocking(
        deviceId: String
    ): Response<DevicesGetDeviceResponse> {
        return toBlocking { getDevice(deviceId) }
    }

    override suspend fun updateDevice(
        request: DevicesUpdateRequest
    ): ResponseUnit {
        return proceedUnit {
            val deviceId = request.deviceId ?: ""
            val body = toJson(
                UpdateDeviceBody(
                    displayName = request.displayName,
                )
            )
            HttpRequest()
                .url("${uri}/_matrix/client/v3/devices/${deviceId}")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .json(body)
                .put()
        }
    }

    override fun updateDeviceBlocking(
        request: DevicesUpdateRequest
    ): ResponseUnit {
        return toBlocking { updateDevice(request) }
    }

    override suspend fun deleteDevice(
        request: DevicesDeleteRequest
    ): ResponseUnit {
        return proceedUnit {
            val deviceId = request.deviceId ?: ""
            HttpRequest()
                .url("${uri}/_matrix/client/v3/devices/${deviceId}")
                .header(AUTHORIZATION, bearerToken())
                .accept(MediaType.JSON)
                .delete()
        }
    }

    override fun deleteDeviceBlocking(
        request: DevicesDeleteRequest
    ): ResponseUnit {
        return toBlocking { deleteDevice(request) }
    }

    @Serializable
    private data class UpdateDeviceBody(
        @SerialName("display_name")
        val displayName: String? = null,
    )
}
