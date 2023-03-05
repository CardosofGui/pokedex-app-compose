package cardosofgui.android.core.navigation.routes

import android.os.Bundle
import cardosofgui.android.core.navigation.AppRoute

class DetailsRoutes : AppRoute(DetailsRoutes) {
    companion object {
        const val POKEMON_ID_PARAM = "POKEMON_ID_PARAM"
        const val DetailsRoutes = "/details"
    }
}