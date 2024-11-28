package com.theikchan.screencloudwebplaybridge.di

import com.theikchan.screencloudwebplaybridge.data.repo.DeviceInfoRepositoryImpl
import com.theikchan.screencloudwebplaybridge.domain.repo.DeviceInfoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDeviceInfoRepository(userRepository: DeviceInfoRepositoryImpl): DeviceInfoRepository
}