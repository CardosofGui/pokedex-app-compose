object CoreVersions {
    const val startup = "1.1.1"
    const val koinCompose = "3.2.1"
    const val lifecycle = "2.5.1"
    const val lifecycleCompose = "2.6.0-alpha01"
    const val androidXCore = "1.9.0"
    const val airbnbDeeplink = "6.1.0"
    const val logcat = "0.1"
    const val coroutines = "1.6.4"
}

object CoreLibs {
    const val startup = "androidx.startup:startup-runtime:${CoreVersions.startup}"
    const val koinCompose = "io.insert-koin:koin-androidx-compose:${CoreVersions.koinCompose}"

    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${CoreVersions.lifecycle}"
    const val lifecycleRuntimeCompose = "androidx.lifecycle:lifecycle-runtime-compose:${CoreVersions.lifecycleCompose}"
    const val androidXCoreKtx = "androidx.core:core-ktx:${CoreVersions.androidXCore}"

    const val airbnbDeeplink = "com.airbnb:deeplinkdispatch:${CoreVersions.airbnbDeeplink}"
    const val airbnbDeeplinkAnnotation = "com.airbnb:deeplinkdispatch-processor:${CoreVersions.airbnbDeeplink}"

    const val logcat = "com.squareup.logcat:logcat:${CoreVersions.logcat}"

    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${CoreVersions.coroutines}"
}