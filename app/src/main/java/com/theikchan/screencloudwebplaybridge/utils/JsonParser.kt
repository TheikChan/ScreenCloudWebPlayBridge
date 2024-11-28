package com.theikchan.screencloudwebplaybridge.utils

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonObject

object JsonParser {
    fun mergeJson(androidInfo: String, nativeDeviceInfo: String) : String {
        val androidJson = Json.parseToJsonElement(androidInfo).jsonObject
        val nativeJson = Json.parseToJsonElement(nativeDeviceInfo).jsonObject

        val mergedJson = buildJsonObject {
            androidJson.forEach { (key, value) ->
                put(key, value)
            }
            put("native_info", nativeJson)
        }

        return mergedJson.toString()
    }
}

