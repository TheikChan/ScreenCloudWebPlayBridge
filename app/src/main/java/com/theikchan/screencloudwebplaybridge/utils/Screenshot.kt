package com.theikchan.screencloudwebplaybridge.utils

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Base64
import android.util.Log
import android.view.PixelCopy
import android.view.Window
import android.webkit.WebView
import androidx.annotation.RequiresApi
import java.io.ByteArrayOutputStream
import java.net.URLEncoder

object Screenshot {

    fun capture(webView: WebView, onResult: (String?) -> Unit) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            capturePixelCopy(webView, onResult)
//        } else {
            captureCanvas(webView, onResult)
//        }
    }

    private fun captureCanvas(screenView: WebView, onResult: (String?) -> Unit) {
        try {
            val bitmap = Bitmap.createBitmap(
                screenView.width,
                screenView.height,
                Bitmap.Config.ARGB_8888
            )

            val canvas = Canvas(bitmap)
            screenView.draw(canvas)

            val base64 = convertBitmapToBase64(bitmap)
            onResult(base64)
        } catch (e: Exception) {
            e.printStackTrace()
            onResult(null)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun capturePixelCopy(
        webView: WebView,
        onResult: (String?) -> Unit
    ) {
        val window = getWindowFrom(webView) ?: return onResult(null)

        val bitmap = Bitmap.createBitmap(webView.width, webView.height, Bitmap.Config.ARGB_8888)

        val location = IntArray(2)
        webView.getLocationInWindow(location)

        val rect = Rect(
            location[0],
            location[1],
            location[0] + webView.width,
            location[1] + webView.height
        )

        try {
            PixelCopy.request(
                window,
                rect,
                bitmap,
                { result ->
                    if (result == PixelCopy.SUCCESS) {
                        val base64 = convertBitmapToBase64(bitmap)
                        onResult(base64)
                    } else {
                        onResult(null)
                    }
                },
                Handler(Looper.getMainLooper())
            )
        } catch (e: Exception) {
            e.printStackTrace()
            onResult(null)
        }
    }

    private fun getWindowFrom(webView: WebView): Window? {
        val context = webView.context
        return if (context is Activity) {
            context.window
        } else {
            null
        }
    }

    private fun convertBitmapToBase64(bm: Bitmap): String {
        val byteArrayStream = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.PNG, 100, byteArrayStream)
        val byteArray = byteArrayStream.toByteArray()
        val base64String = Base64.encodeToString(byteArray, Base64.DEFAULT)
        val base64UrlEncodedString = URLEncoder.encode(base64String, "UTF-8")
        Log.d("[Screenshot][Base64]", base64UrlEncodedString)
        return base64UrlEncodedString
    }
}

