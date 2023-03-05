package cardosofgui.android.pokedexcompose.feature.pokemons.navigation

import android.content.Context
import android.content.Intent
import cardosofgui.android.core.navigation.AppDeepLink
import cardosofgui.android.core.navigation.routes.PokemonsRoutes
import cardosofgui.android.pokedexcompose.feature.pokemons.ui.main.PokemonsActivity

object PokemonsNavigation {

    @JvmStatic
    @AppDeepLink(PokemonsRoutes.PokemonsRoutes)
    fun openPokemons(context: Context): Intent {
        return PokemonsActivity.getStartIntent(context)
    }
}
