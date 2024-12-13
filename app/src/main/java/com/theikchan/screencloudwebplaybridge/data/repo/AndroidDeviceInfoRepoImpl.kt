package com.theikchan.screencloudwebplaybridge.data.repo

import android.content.Context
import android.os.Build
import com.theikchan.screencloudwebplaybridge.domain.repo.AndroidDeviceInfoRepo
import javax.inject.Inject

class AndroidDeviceInfoRepoImpl @Inject constructor(private val context: Context): AndroidDeviceInfoRepo {
        override fun getAppVersion() : String {
            return context.packageManager.getPackageInfo(context.packageName, 0).versionName
        }

        override fun getPackageName() : String {
            return context.packageName
        }

        override fun getScreenWidth() : Int {
            return context.resources.displayMetrics.widthPixels
        }

        override fun getScreenHeight(): Int {
            return context.resources.displayMetrics.heightPixels
        }

        override fun getScreenDensity(): Int {
            return context.resources.displayMetrics.density.toInt()
        }

        override fun getAndroidVersion() : Int {
            return Build.VERSION.SDK_INT
        }

        override fun getManufacturer(): String {
            return  Build.MANUFACTURER
        }

        override fun getModel() : String {
            return Build.MODEL
        }
//    return """{
//            "app_version": "1.0",
//            "package_name": "io.screencloud.assignment.android_senior_dev",
//            "screen_width": 2560,
//            "screen_height": 1688,
//            "screen_density": 2,
//            "android_version": 30,
//            "device_manufacturer": "Genymobile",
//            "device_model": "Pixel C"
//        }"""
}