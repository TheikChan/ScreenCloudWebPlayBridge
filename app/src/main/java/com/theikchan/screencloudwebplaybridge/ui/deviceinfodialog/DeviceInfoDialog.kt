package com.theikchan.screencloudwebplaybridge.ui.deviceinfodialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.theikchan.screencloudwebplaybridge.R

@Composable
fun DeviceInfoDialog(
    viewModel: DeviceInfoDialogViewModel = viewModel(),
    onClosePress: () -> Unit,
) {

    val deviceDetails by viewModel.deviceDetailInfoList.observeAsState(emptyList())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .background(Color.Black.copy(alpha = 0.5f))
            .testTag("deviceInfoDialog"),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                stringResource(R.string.device_information_title_label),
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            deviceDetails.forEach { (key, value) ->
                DeviceInfoItemView(title = key, value = value)
            }
        }

        TextButton(
            onClick = onClosePress,
            modifier = Modifier
                .align(alignment = Alignment.BottomEnd)
                .padding(8.dp)
                .testTag("deviceInfoDialogCloseButton")
        ) {
            Text(stringResource(R.string.close_button_label))
        }
    }
}

@Preview
@Composable
fun PreviewDeviceInfoDialog() {
    MaterialTheme {
        DeviceInfoDialog(
            onClosePress = {},
        )
    }
}