package com.theikchan.screencloudwebplaybridge.domain.model

import com.theikchan.screencloudwebplaybridge.navigation.DeviceDetailInfoPara
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class DeviceDetailInfo(
    @SerialName("app_version") val appVersion: String,
    @SerialName("package_name") val packageName: String,
    @SerialName("screen_width") val screenWidth: Int,
    @SerialName("screen_height") val screenHeight: Int,
    @SerialName("screen_density") val screenDensity: Int,
    @SerialName("android_version") val androidVersion: Int,
    @SerialName("device_manufacturer") val deviceManufacturer: String,
    @SerialName("device_model") val deviceModel: String,
    @SerialName("native_info") val nativeInfo: NativeInfo
){
    fun toJsonString() : String {
        return Json.encodeToString(this)
    }
}

fun DeviceDetailInfo.toDetailPara(): DeviceDetailInfoPara {
    return DeviceDetailInfoPara(
        appVersion = this.appVersion,
        packageName = this.packageName,
        screenWidth = this.screenWidth,
        screenHeight = this.screenHeight,
        screenDensity = this.screenDensity.toDouble(),
        androidVersion = this.androidVersion,
        deviceManufacturer = this.deviceManufacturer,
        deviceModel = this.deviceModel,
        ramTotal = this.nativeInfo.ramTotal,
        cpuCores = this.nativeInfo.cpuInfo.cpuCores,
        cpuFreq = this.nativeInfo.cpuInfo.cpuFreq,
        kernelVersion = this.nativeInfo.kernelVersion,
        buildFingerprint = this.nativeInfo.buildFingerprint,
        hardwareSerial = this.nativeInfo.hardwareSerial
    )
}

@Serializable
data class NativeInfo(
    @SerialName("ram_total") val ramTotal: String,
    @SerialName("cpu_info") val cpuInfo: CpuInfo,
    @SerialName("kernel_version") val kernelVersion: String,
    @SerialName("build_fingerprint") val buildFingerprint: String,
    @SerialName("hardware_serial") val hardwareSerial: String
)

@Serializable
data class CpuInfo(
    @SerialName("cpu_cores") val cpuCores: String,
    @SerialName("cpu_freq") val cpuFreq: String
)

//{
//    "app_version": "1.0",
//    "package_name": "io.screencloud.assignment.android_senior_dev",
//    "screen_width": 2560, // Screen resolution
//    "screen_height": 1688, // Screen resolution
//    "screen_density": 2,
//    "android_version": 30,
//    "device_manufacturer": "Genymobile",
//    "device_model": "Pixel C",
//    "native_info": {
//        "ram_total": "3148120064",
//        "cpu_info": {
//            "cpu_cores": "3",
//            "cpu_freq": "3072.000"
//        },
//        "kernel_version": "11",
//        "build_fingerprint": "google/vbox86p/vbox86p:11/RQ1A.210105.003/857:userdebug/test-keys,p1",
//        "hardware_serial": "vbox86"
//    }
//}