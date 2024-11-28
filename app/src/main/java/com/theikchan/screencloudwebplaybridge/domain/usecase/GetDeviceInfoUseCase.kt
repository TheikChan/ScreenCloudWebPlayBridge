package com.theikchan.screencloudwebplaybridge.domain.usecase

import com.theikchan.screencloudwebplaybridge.domain.model.DeviceDetailInfo
import com.theikchan.screencloudwebplaybridge.domain.repo.DeviceInfoRepository

class GetDeviceInfoUseCase(private val deviceRepository: DeviceInfoRepository) {
    suspend fun provide(): DeviceDetailInfo {
        return deviceRepository.getDeviceDetailInfo()
    }
}