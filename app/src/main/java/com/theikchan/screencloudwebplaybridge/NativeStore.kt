package com.theikchan.screencloudwebplaybridge

class NativeStore {
    external fun getDeviceInfo(): String

    companion object {
        init {
            System.loadLibrary("screencloudwebplaybridge")
        }
    }
}