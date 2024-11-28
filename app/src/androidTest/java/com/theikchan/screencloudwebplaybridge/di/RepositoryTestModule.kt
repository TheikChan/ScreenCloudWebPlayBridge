package com.theikchan.screencloudwebplaybridge.di

import com.theikchan.screencloudwebplaybridge.domain.repo.DeviceInfoRepository
import com.theikchan.screencloudwebplaybridge.repo.FakeDeviceInfoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
abstract class RepositoryTestModule {
    @Singleton
    @Binds
    abstract fun bindFakeDeviceInfoRepository(fakeDeviceInfoRepositoryImpl: FakeDeviceInfoRepository): DeviceInfoRepository
}