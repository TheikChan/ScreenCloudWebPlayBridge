package com.theikchan.screencloudwebplaybridge.ui.deviceinfodialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DeviceInfoItemView(title: String, value: String) {
    Column {
        Text(
            title,
            style = MaterialTheme.typography.titleMedium
        )
        Text(value)
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview
@Composable
fun PreviewDeviceDetailRow() {
    MaterialTheme {
        DeviceInfoItemView(
            title = "Device Title",
            value = "Device Value"
        )
    }
}