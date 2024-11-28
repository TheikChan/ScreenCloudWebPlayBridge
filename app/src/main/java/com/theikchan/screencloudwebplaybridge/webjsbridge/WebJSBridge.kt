package com.theikchan.screencloudwebplaybridge.webjsbridge

import android.webkit.JavascriptInterface
import android.webkit.WebView
import com.theikchan.screencloudwebplaybridge.utils.Screenshot

class WebJSBridge(
    private val webView: WebView,
    private val deviceInfo: String,
    private val onScreenShotResult: (isSuccess: Boolean, data: String?) -> Unit
) :
    SC_INTERFACE {

    init {
        webView.apply {
            addJavascriptInterface(this@WebJSBridge, JSBridgeConstant.BRIDGE_NAME)
        }
    }

    @JavascriptInterface
    override fun device_info(): String {
        return deviceInfo
    }

    @JavascriptInterface
    override fun take_screenshot(callback: String) {
        Screenshot.capture(webView) { base64EncodedString ->
            if (base64EncodedString != null) {
                sendDataToWebView(callback, base64EncodedString)
                onScreenShotResult(true, base64EncodedString)
            } else {
                onScreenShotResult(false, null)
            }
        }
    }

    @JavascriptInterface
    override fun get_name(): String {
        return "Theik Chan"
    }

    private fun sendDataToWebView(callback: String, data: String) {
        webView.post {
            webView.evaluateJavascript("javascript:$callback('$data')", null)
        }
    }
}
