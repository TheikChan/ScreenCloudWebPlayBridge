#include <jni.h>
#include <string>

std::string getDeviceInfoJson() {
    std::string native_info = R"({
        "ram_total": "3148120064",
        "cpu_info": {
            "cpu_cores": "3",
            "cpu_freq": "3072.000"
        },
        "kernel_version": "11",
        "build_fingerprint": "google/vbox86p/vbox86p:11/RQ1A.210105.003/857:userdebug/test-keys,p1",
        "hardware_serial": "vbox86"
    })";

    return native_info;
}

extern "C"
JNIEXPORT jstring JNICALL Java_com_theikchan_screencloudwebplaybridge_NativeStore_getDeviceInfo(JNIEnv* env, jobject /* this */) {
    std::string json = getDeviceInfoJson();
    return env->NewStringUTF(json.c_str());
}