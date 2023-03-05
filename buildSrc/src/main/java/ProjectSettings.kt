object AndroidSDK {
    const val minSDK = 24
    const val targetSDK = 33
    const val compileSDK = 33
}

object KotlinSettings {
    const val jvmTarget = "1.8"
}

object AppSettings {
    const val versionCode = 1
    const val versionName = "1.0.0"

    const val debuggable = false
}

object Features {
    const val components = ":core:components"
    const val data = ":core:data"
    const val domain = ":core:domain"
    const val navigation = ":core:navigation"

    const val pokemons = ":feature:pokemons"
    const val details = ":feature:details"
}

object ProjectVersions {
    const val kotlin = "1.7.0"
}
object ProjectLibs {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${ProjectVersions.kotlin}"
}

object ProjectPlugins {
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${ProjectVersions.kotlin}"
}