object CoreVersions {
    const val startup = "1.1.1"
    const val koinCompose = "3.2.1"
    const val lifecycle = "2.5.1"
    const val androidXCore = "1.9.0"
    const val airbnbDeeplink = "6.1.0"
    const val logcat = "0.1"
}

object CoreLibs {
    const val startup = "androidx.startup:startup-runtime:${CoreVersions.startup}"
    const val koinCompose = "io.insert-koin:koin-androidx-compose:${CoreVersions.koinCompose}"

    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${CoreVersions.lifecycle}"
    const val androidXCoreKtx = "androidx.core:core-ktx:${CoreVersions.androidXCore}"

    const val airbnbDeeplink = "com.airbnb:deeplinkdispatch:${CoreVersions.airbnbDeeplink}"
    const val airbnbDeeplinkAnnotation = "com.airbnb:deeplinkdispatch-processor:${CoreVersions.airbnbDeeplink}"

    const val logcat = "com.squareup.logcat:logcat:${CoreVersions.logcat}"
}