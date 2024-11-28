package com.theikchan.screencloudwebplaybridge.domain.usecase

import com.theikchan.screencloudwebplaybridge.domain.model.DeviceDetailInfo
import com.theikchan.screencloudwebplaybridge.domain.repo.DeviceInfoRepository
import javax.inject.Inject

class GetDeviceInfoUseCase @Inject constructor(private val deviceRepository: DeviceInfoRepository) {
    suspend fun provide(): DeviceDetailInfo {
        return deviceRepository.getDeviceDetailInfo()
    }
}