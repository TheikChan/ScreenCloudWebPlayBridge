package com.theikchan.screencloudwebplaybridge.domain.repo

interface AndroidDeviceInfoRepo {
    fun getAppVersion(): String

    fun getPackageName(): String

    fun getScreenWidth(): Int

    fun getScreenHeight(): Int

    fun getScreenDensity(): Int

    fun getAndroidVersion(): Int

    fun getManufacturer(): String

    fun getModel(): String
}