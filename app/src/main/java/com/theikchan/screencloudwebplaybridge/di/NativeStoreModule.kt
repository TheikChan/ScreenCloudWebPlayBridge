package com.theikchan.screencloudwebplaybridge.di

import com.theikchan.screencloudwebplaybridge.NativeStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NativeStoreModule {

    @Provides
    @Singleton
    fun provideNativeStore(): NativeStore {
        return NativeStore()
    }
}