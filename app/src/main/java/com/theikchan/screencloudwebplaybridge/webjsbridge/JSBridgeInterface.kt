package com.theikchan.screencloudwebplaybridge.webjsbridge

import android.webkit.JavascriptInterface

interface SC_INTERFACE {
    @JavascriptInterface
    fun device_info(): String // Returns JSON of device info

    @JavascriptInterface
    fun take_screenshot(callback: String) // will capture content and send base64 string image data to callback function

    @JavascriptInterface
    fun get_name(): String // return your name
}