package com.theikchan.screencloudwebplaybridge.repo

import com.theikchan.screencloudwebplaybridge.domain.model.DeviceDetailInfo
import com.theikchan.screencloudwebplaybridge.domain.repo.DeviceInfoRepository
import com.theikchan.screencloudwebplaybridge.utils.JsonParser
import kotlinx.serialization.json.Json
import javax.inject.Inject

class FakeDeviceInfoRepository @Inject constructor() : DeviceInfoRepository {

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

    override suspend fun getNativeDeviceInfo(): String {
        return """{
            "ram_total": "3148120064",
            "cpu_info": {
                "cpu_cores": "3",
                "cpu_freq": "3072.000"
            },
            "kernel_version": "11",
            "build_fingerprint": "google/vbox86p/vbox86p:11/RQ1A.210105.003/857:userdebug/test-keys,p1",
            "hardware_serial": "vbox86"
            }
        """.trimIndent()
    }

    override suspend fun getDeviceDetailInfo(): DeviceDetailInfo {
        val nativeDeviceInfo = getNativeDeviceInfo()
        val androidDeviceInfo = getAndroidDeviceInfo()
        val deviceDetailInfoString = JsonParser.mergeJson(androidDeviceInfo, nativeDeviceInfo)
        return Json.decodeFromString(deviceDetailInfoString)
    }
}


