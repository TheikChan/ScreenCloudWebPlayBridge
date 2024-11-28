package com.theikchan.screencloudwebplaybridge.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.theikchan.screencloudwebplaybridge.ui.deviceinfodialog.DeviceInfoDialog
import com.theikchan.screencloudwebplaybridge.ui.deviceinfodialog.DeviceInfoDialogViewModel
import com.theikchan.screencloudwebplaybridge.ui.main.ScreenCloudWebPlay
import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class DeviceDetailInfoPara(
    val appVersion: String,
    val packageName: String,
    val screenWidth: Int,
    val screenHeight: Int,
    val screenDensity: Double,
    val androidVersion: Int,
    val deviceManufacturer: String,
    val deviceModel: String,
    val ramTotal: String,
    val cpuCores: String,
    val cpuFreq: String,
    val kernelVersion: String,
    val buildFingerprint: String,
    val hardwareSerial: String
)

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> {
            ScreenCloudWebPlay(
                onDeviceInfoPressed = { deviceDetailParam ->
                    navController.navigate(deviceDetailParam)
                },
                onScreenshotPressed = {}
            )
        }

        dialog<DeviceDetailInfoPara> { backStackEntry ->
            val deviceDetailParam: DeviceDetailInfoPara = backStackEntry.toRoute()
            val viewModel = remember { DeviceInfoDialogViewModel(deviceDetailParam) }

            DeviceInfoDialog(
                viewModel,
                onClosePress = {
                    navController.popBackStack()
                }
            )
        }
    }
}