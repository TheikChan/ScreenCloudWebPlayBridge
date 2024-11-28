package com.theikchan.screencloudwebplaybridge.data.repo

import com.theikchan.screencloudwebplaybridge.NativeStore
import com.theikchan.screencloudwebplaybridge.domain.model.DeviceDetailInfo
import com.theikchan.screencloudwebplaybridge.domain.repo.DeviceInfoRepository
import com.theikchan.screencloudwebplaybridge.utils.JsonParser
import kotlinx.serialization.json.Json

class DeviceInfoRepositoryImpl(
    private val nativeStore: NativeStore
) : DeviceInfoRepository {

    override suspend fun getAndroidDeviceInfo(): String {
        return """{
            "app_version": "1.0",
            "package_name": "io.screencloud.assignment.android_senior_dev",
            "screen_width": 2560,
            "screen_height": 1688,
            "screen_density": 2,
            "android_version": 30,
            "device_manufacturer": "Genymobile",
            "device_model": "Pixel C"
        }"""
    }

    override suspend fun getNativeDeviceInfo(): String =
        nativeStore.getDeviceInfo()

    override suspend fun getDeviceDetailInfo(): DeviceDetailInfo {
        val nativeDeviceInfo = nativeStore.getDeviceInfo()
        val androidDeviceInfo = getAndroidDeviceInfo()
        val deviceDetailInfoString = JsonParser.mergeJson(androidDeviceInfo, nativeDeviceInfo)
        return Json.decodeFromString(deviceDetailInfoString)
    }
}