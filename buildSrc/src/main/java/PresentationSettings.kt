object PresentationVersions {
    const val composeCompiler = "1.3.2"
    const val composeBom = "2022.10.00"
    const val composeActivity = "1.6.1"
    const val composeCoil = "2.2.2"
    const val lottie = "6.0.0"
}

object PresentationLibs {
    const val composeBom = "androidx.compose:compose-bom:${PresentationVersions.composeBom}"
    const val composeActivity = "androidx.activity:activity-compose:${PresentationVersions.composeActivity}"
    const val composeUi = "androidx.compose.ui:ui"
    const val composeGraphics = "androidx.compose.ui:ui-graphics"
    const val composePreview = "androidx.compose.ui:ui-tooling-preview"
    const val composeMaterial3 = "androidx.compose.material3:material3"
    const val composeCoil = "io.coil-kt:coil-compose:${PresentationVersions.composeCoil}"

    const val lottie = "com.airbnb.android:lottie:${PresentationVersions.lottie}"
    const val lottieCompose = "com.airbnb.android:lottie-compose:${PresentationVersions.lottie}"
}