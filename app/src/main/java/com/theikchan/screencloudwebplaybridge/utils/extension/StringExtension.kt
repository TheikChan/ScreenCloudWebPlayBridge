package com.theikchan.screencloudwebplaybridge.utils.extension

fun String.displayRamSize(): String {
    val ramTotalInBytes = this.toLong()
    val ramTotalInGB = ramTotalInBytes / (1024 * 1024 * 1024).toDouble()
    return "%.2f".format(ramTotalInGB)
}