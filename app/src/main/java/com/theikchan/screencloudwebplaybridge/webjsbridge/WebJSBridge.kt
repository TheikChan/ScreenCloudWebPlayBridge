package com.theikchan.screencloudwebplaybridge.webjsbridge

import android.webkit.JavascriptInterface
import android.webkit.WebView
import com.theikchan.screencloudwebplaybridge.utils.Screenshot

class WebJSBridge(
    private val webView: WebView,
    private val deviceInfo: String) :
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
            base64EncodedString?.let {
                sendDataToWebView(callback, base64EncodedString)
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
