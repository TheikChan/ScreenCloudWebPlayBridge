package com.theikchan.screencloudwebplaybridge.ui.deviceinfodialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.theikchan.screencloudwebplaybridge.navigation.DeviceDetailInfoPara
import com.theikchan.screencloudwebplaybridge.utils.extension.displayRamSize

class DeviceInfoDialogViewModel(private val deviceDetailInfo: DeviceDetailInfoPara) : ViewModel() {

    private val _deviceDetailInfoList = MutableLiveData<List<Pair<String, String>>>()
    val deviceDetailInfoList: LiveData<List<Pair<String, String>>> = _deviceDetailInfoList

    init {
        _deviceDetailInfoList.value = getFormattedDeviceInfo()
    }

    private fun getFormattedDeviceInfo(): List<Pair<String, String>> {
        return listOf("App Info:" to "Version: ${deviceDetailInfo.appVersion}\nPackage: ${deviceDetailInfo.packageName}",
            "Screen Info:" to "Width: ${deviceDetailInfo.screenWidth}px\nHeight: ${deviceDetailInfo.screenHeight}px\nDensity: ${deviceDetailInfo.screenDensity}",
            "Device Info:" to "Manufacturer: ${deviceDetailInfo.deviceManufacturer}\nModel: ${deviceDetailInfo.deviceModel}\nAndroid Version: ${deviceDetailInfo.androidVersion}",
            "CPU Info:" to "Cores: ${deviceDetailInfo.cpuCores}\nFrequency: ${deviceDetailInfo.cpuFreq} MHz",
            "System Info:" to "RAM Total: ${deviceDetailInfo.ramTotal.displayRamSize()} GB\nKernel Version: ${deviceDetailInfo.kernelVersion}",)
    }
}