package com.theikchan.screencloudwebplaybridge.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.theikchan.screencloudwebplaybridge.navigation.AppNavigation
import com.theikchan.screencloudwebplaybridge.ui.theme.ScreenCloudWebPlayBridgeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScreenCloudWebPlayBridgeTheme {
                AppNavigation()
            }
        }
    }
}