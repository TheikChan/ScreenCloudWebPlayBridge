package com.theikchan.screencloudwebplaybridge.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.theikchan.screencloudwebplaybridge.NativeStore
import com.theikchan.screencloudwebplaybridge.data.repo.DeviceInfoRepositoryImpl
import com.theikchan.screencloudwebplaybridge.domain.usecase.GetDeviceInfoUseCase
import com.theikchan.screencloudwebplaybridge.ui.deviceinfodialog.DeviceInfoDialog
import com.theikchan.screencloudwebplaybridge.ui.deviceinfodialog.DeviceInfoDialogViewModel
import com.theikchan.screencloudwebplaybridge.ui.main.ScreenCloudWebPlay
import com.theikchan.screencloudwebplaybridge.ui.main.ScreenCloudWebPlayViewModel
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
            val nativeStore = remember { NativeStore() }
            val repository = remember { DeviceInfoRepositoryImpl(nativeStore) }
            val useCase = remember { GetDeviceInfoUseCase(repository) }
            val viewModel = remember { ScreenCloudWebPlayViewModel(useCase) }
//            val viewModel: ScreenCloudWebPlayViewModel = viewModel(
//                factory = ScreenCloudWebPlayViewModelFactory(useCase)
//            )

            ScreenCloudWebPlay(
                viewModel = viewModel,
                onDeviceInfoPressed = { deviceDetailParam ->
                    navController.navigate(deviceDetailParam)
                }
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

//class ScreenCloudWebPlayViewModelFactory(
//    private val useCase: GetDeviceInfoUseCase,
//) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(ScreenCloudWebPlayViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return ScreenCloudWebPlayViewModel(useCase) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}