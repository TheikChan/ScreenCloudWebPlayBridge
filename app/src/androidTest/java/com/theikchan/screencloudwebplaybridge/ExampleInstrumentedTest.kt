package com.theikchan.screencloudwebplaybridge

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.theikchan.screencloudwebplaybridge.data.repo.DeviceInfoRepositoryImpl
import com.theikchan.screencloudwebplaybridge.domain.usecase.GetDeviceInfoUseCase
import com.theikchan.screencloudwebplaybridge.navigation.DeviceDetailInfoPara
import com.theikchan.screencloudwebplaybridge.ui.deviceinfodialog.DeviceInfoDialog
import com.theikchan.screencloudwebplaybridge.ui.deviceinfodialog.DeviceInfoDialogViewModel
import com.theikchan.screencloudwebplaybridge.ui.main.ScreenCloudWebPlay
import com.theikchan.screencloudwebplaybridge.ui.main.ScreenCloudWebPlayViewModel
import com.theikchan.screencloudwebplaybridge.ui.theme.ScreenCloudWebPlayBridgeTheme
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private lateinit var mockViewModel: ScreenCloudWebPlayViewModel


    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        val mockNativeStore = NativeStore()
        val mockDeviceInfoRepo = DeviceInfoRepositoryImpl(mockNativeStore)
        val mockGetDeviceInfoUseCase = GetDeviceInfoUseCase(mockDeviceInfoRepo)

        mockViewModel = ScreenCloudWebPlayViewModel(
            getDeviceInfoUseCase = mockGetDeviceInfoUseCase,
        )
    }

    @Test
    fun testWebViewLoading() {
        // Test - WebView loading verification
        // Launch the app
        // Check WebView exist
        // check WebView loading finished
        composeTestRule.setContent {
            ScreenCloudWebPlay(viewModel = mockViewModel, onDeviceInfoPressed = {})
        }

        // Assert WebView exists
        composeTestRule.onNodeWithTag("webView").assertExists()

        // Assert WebView successfully loaded
        assertTrue(mockViewModel.isWebViewLoaded.value ?: false)
    }

    @Test
    fun testScreenshot() {
        // Test - Screenshot verification
        // Launch the app
        // Check screenshot button exist
        // Perform click on screenshot button
        composeTestRule.setContent {
            ScreenCloudWebPlay(viewModel = mockViewModel, onDeviceInfoPressed = {})
        }

        // Assert WebView exists
        composeTestRule.onNodeWithTag("webView").assertExists()

        // Simulate the user clicking the screenshot button
        composeTestRule.onNodeWithTag("screenshotButton").performClick()
    }

    @Test
    fun testDeviceInfo() {
        // Test - Device info accuracy
        // Launch the app
        // Check device info button exist
        // Perform click on device info button
        // Check device info dialog exist
        // Check device info dialog content correct
        // Close the info dialog
        // Check the info dialog is dismissed

        // Arrange: Mock the data for testing
        val mockDeviceInfo = DeviceDetailInfoPara(
            appVersion = "1.0",
            packageName = "io.screencloud.assignment.android_senior_dev",
            screenWidth = 2560,
            screenHeight = 1688,
            screenDensity = 2.0,
            deviceManufacturer = "Genymobile",
            deviceModel = "Pixel C",
            cpuCores = "3",
            cpuFreq = "3072.000",
            ramTotal = "3148120064",
            kernelVersion = "11",
            buildFingerprint = "google/vbox86p/vbox86p:11/RQ1A.210105.003/857:userdebug/test-keys,p1",
            hardwareSerial = "vbox86",
            androidVersion = 30,
        )

        // Act: Render the dialog with mocked data
        composeTestRule.setContent {
            ScreenCloudWebPlayBridgeTheme {
                DeviceInfoDialog(
                    viewModel = DeviceInfoDialogViewModel(mockDeviceInfo),
                    onClosePress = {}
                )
            }
        }

        // Assert: Verify that the correct information is displayed
        composeTestRule.onNodeWithText("Device Information").assertIsDisplayed()

        composeTestRule.onNodeWithText("App Info:").assertIsDisplayed()
        composeTestRule.onNodeWithText("Version: 1.0\nPackage: io.screencloud.assignment.android_senior_dev")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Screen Info:").assertIsDisplayed()
        composeTestRule.onNodeWithText("Width: 2560px\nHeight: 1688px\nDensity: 2.0")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Device Info:").assertIsDisplayed()
        composeTestRule.onNodeWithText("Manufacturer: Genymobile\nModel: Pixel C\nAndroid Version: 30")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("CPU Info:").assertIsDisplayed()
        composeTestRule.onNodeWithText("Cores: 3\nFrequency: 3072.000 MHz").assertIsDisplayed()

        composeTestRule.onNodeWithText("System Info:").assertIsDisplayed()
        composeTestRule.onNodeWithText("RAM Total: 2.93 GB\nKernel Version: 11").assertIsDisplayed()

        composeTestRule.onNodeWithTag("deviceInfoDialogCloseButton").assertIsDisplayed()
    }
}