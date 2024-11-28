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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


data class ScreenCloudViewState(
    val webView: WebView? = null,
    val webJsBridge: WebJSBridge? = null
)

@HiltViewModel
class ScreenCloudWebPlayViewModel @Inject constructor(private val getDeviceInfoUseCase: GetDeviceInfoUseCase) :
    ViewModel() {

    private val _viewState = MutableLiveData(ScreenCloudViewState())
    val viewState: LiveData<ScreenCloudViewState> = _viewState

    private val _deviceDetailInfo = MutableLiveData<DeviceDetailInfo>()
    val deviceDetailInfo: LiveData<DeviceDetailInfo> = _deviceDetailInfo

    private val _isWebViewLoaded = MutableLiveData<Boolean>()
    val isWebViewLoaded: LiveData<Boolean> = _isWebViewLoaded

    private val _webViewLoadedUrl = MutableLiveData<String>()
    val webViewLoadedUrl: LiveData<String> = _webViewLoadedUrl

    private val _isScreenshotSuccess = MutableLiveData<Boolean>()
    val isScreenshotSuccess: LiveData<Boolean> = _isScreenshotSuccess

    private val _screenshotBase64 = MutableLiveData<String?>()
    val screenshotBase64: LiveData<String?> = _screenshotBase64

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
                WebJSBridge(
                    createdWebView, deviceDetailInfo.value?.toJsonString() ?: "",
                    onScreenShotResult = ::handleScreenshotResult
                )

            _viewState.value = _viewState.value?.copy(
                webView = createdWebView,
                webJsBridge = webJsBridge
            )
        }
    }

    private fun handleScreenshotResult(isSuccess: Boolean, base64Screenshot: String?) {
        viewModelScope.launch {
            _isScreenshotSuccess.value = isSuccess
            _screenshotBase64.value = base64Screenshot
        }
    }

    fun onWebViewLoaded(isLoaded: Boolean, url: String) {
        viewModelScope.launch {
            _isWebViewLoaded.value = isLoaded
            _webViewLoadedUrl.value = url
        }
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