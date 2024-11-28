package com.theikchan.screencloudwebplaybridge.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.theikchan.screencloudwebplaybridge.R
import com.theikchan.screencloudwebplaybridge.domain.model.toDetailPara
import com.theikchan.screencloudwebplaybridge.navigation.DeviceDetailInfoPara
import com.theikchan.screencloudwebplaybridge.ui.component.WebView

@Composable
fun ScreenCloudWebPlay(
    viewModel: ScreenCloudWebPlayViewModel = hiltViewModel(),
    onDeviceInfoPressed: (DeviceDetailInfoPara) -> Unit,
    onScreenshotPressed: (Boolean) -> Unit
) {

    val deviceDetailInfo by viewModel.deviceDetailInfo.observeAsState(null)
    val isScreenshotSuccess by viewModel.isScreenshotSuccess.observeAsState(null)

    LaunchedEffect(isScreenshotSuccess) {
        isScreenshotSuccess?.let { onScreenshotPressed(it) }
    }

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .safeDrawingPadding()
                .fillMaxSize()
        ) {
            WebView(
                url = viewModel.webViewUrl,
                modifier = Modifier.testTag("webView"),
                onWebViewCreated = { createdWebView ->
                    viewModel.onWebViewCreated(createdWebView)
                },
                onWebViewLoaded = { isLoaded, url ->
                    viewModel.onWebViewLoaded(isLoaded, url)
                }
            )

            Row(
                modifier = Modifier
                    .align(alignment = Alignment.TopEnd)
                    .padding(16.dp)
            ) {
                Button(
                    onClick = {
                        deviceDetailInfo?.toDetailPara()?.let(onDeviceInfoPressed)
                    },
                    modifier = Modifier.testTag("deviceInfoButton")
                ) {
                    Text(stringResource(R.string.device_info_label))
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        viewModel.takeScreenshot()
                    },
                    modifier = Modifier.testTag("screenshotButton")
                ) {
                    Text(stringResource(R.string.screenshot_button_label))
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewConversation() {
    MaterialTheme {
        ScreenCloudWebPlay(
            onDeviceInfoPressed = { },
            onScreenshotPressed = { }
        )
    }
}