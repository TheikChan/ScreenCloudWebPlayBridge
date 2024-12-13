package com.theikchan.screencloudwebplaybridge.di

import com.theikchan.screencloudwebplaybridge.domain.repo.AndroidDeviceInfoRepo
import com.theikchan.screencloudwebplaybridge.domain.repo.DeviceInfoRepository
import com.theikchan.screencloudwebplaybridge.repo.FakeAndroidDeviceInfoRepository
import com.theikchan.screencloudwebplaybridge.repo.FakeDeviceInfoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [ViewModelComponent::class],
    replaces = [RepositoryModule::class]
)
abstract class RepositoryTestModule {
    @ViewModelScoped
    @Binds
    abstract fun bindFakeDeviceInfoRepository(fakeDeviceInfoRepositoryImpl: FakeDeviceInfoRepository): DeviceInfoRepository

    @ViewModelScoped
    @Binds
    abstract fun bindFakeAndroidDeviceInfoRepository(fakeDeviceInfoRepositoryImpl: FakeAndroidDeviceInfoRepository): AndroidDeviceInfoRepo
}