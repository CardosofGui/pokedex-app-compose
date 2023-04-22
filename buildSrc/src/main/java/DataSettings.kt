object DataVersions {
    const val ktor = "2.2.4"
    const val room = "2.5.0"
}

object DataLibs {
    const val ktorOkhttp = "io.ktor:ktor-client-okhttp:${DataVersions.ktor}"
    const val ktorLogging = "io.ktor:ktor-client-logging-jvm:${DataVersions.ktor}"
    const val ktorContentNegotiation = "io.ktor:ktor-client-content-negotiation:${DataVersions.ktor}"
    const val ktorKotlinxSerialization = "io.ktor:ktor-serialization-kotlinx-json:${DataVersions.ktor}"
    const val roomRuntime = "androidx.room:room-runtime:${DataVersions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${DataVersions.room}"
    const val roomKtx = "androidx.room:room-ktx:${DataVersions.room}"
}