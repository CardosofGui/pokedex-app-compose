package cardosofgui.android.core.navigation

import android.os.Bundle

const val PREFIX_PATH = "pokedexcompose://app"
abstract class AppRoute(
    open val path: String,
    open val bundle: Bundle? = null
) {
    val prefix = PREFIX_PATH
}