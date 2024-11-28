package com.theikchan.screencloudwebplaybridge.ui.main

import android.webkit.WebView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theikchan.screencloudwebplaybridge.domain.model.DeviceDetailInfo
import com.theikchan.screencloudwebplaybridge.domain.usecase.GetDeviceInfoUseCase
import com.theikchan.screencloudwebplaybridge.webjsbridge.JSBridgeConstant
import com.theikchan.screencloudwebplaybridge.webjsbridge.WebJSBridge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


data class ScreenCloudViewState(
    val webView: WebView? = null,
    val webJsBridge: WebJSBridge? = null
)

class ScreenCloudWebPlayViewModel(private val getDeviceInfoUseCase: GetDeviceInfoUseCase) : ViewModel() {

    private val _viewState = MutableLiveData(ScreenCloudViewState())
    val viewState: LiveData<ScreenCloudViewState> = _viewState

    private val _deviceDetailInfo = MutableLiveData<DeviceDetailInfo>()
    val deviceDetailInfo: LiveData<DeviceDetailInfo> = _deviceDetailInfo

    private val _isWebViewLoaded = MutableLiveData<Boolean>()
    val isWebViewLoaded: LiveData<Boolean> = _isWebViewLoaded

    val webViewUrl = "file:///android_asset/index.html"

    init {
        getDeviceDetailInfo()
    }

    private fun getDeviceDetailInfo() {
        viewModelScope.launch {
            _deviceDetailInfo.value = getDeviceInfoUseCase.provide()
        }
    }

    fun onWebViewCreated(createdWebView: WebView) {
        viewModelScope.launch(Dispatchers.Main) {
            val webJsBridge =
                WebJSBridge(createdWebView, deviceDetailInfo.value?.toJsonString() ?: "")

            _viewState.value = _viewState.value?.copy(
                webView = createdWebView,
                webJsBridge = webJsBridge
            )
        }
    }

    fun onWebViewLoaded(isLoaded: Boolean, url: String) {
        _isWebViewLoaded.value = isLoaded
    }

    fun takeScreenshot() {
        viewModelScope.launch(Dispatchers.Main) {
            _viewState.value?.webView?.evaluateJavascript(
                JSBridgeConstant.TAKE_SCREENSHOT_ACTION,
                null
            )
        }
    }
}