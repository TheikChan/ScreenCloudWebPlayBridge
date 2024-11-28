package com.theikchan.screencloudwebplaybridge.domain.repo

import com.theikchan.screencloudwebplaybridge.domain.model.DeviceDetailInfo

interface DeviceInfoRepository {
    suspend fun getAndroidDeviceInfo(): String
    suspend fun getNativeDeviceInfo(): String
    suspend fun getDeviceDetailInfo(): DeviceDetailInfo
}