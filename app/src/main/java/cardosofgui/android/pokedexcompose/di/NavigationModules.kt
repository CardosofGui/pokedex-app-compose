package cardosofgui.android.pokedexcompose.di

import cardosofgui.android.core.navigation.AppDeeplinkNavigation
import cardosofgui.android.pokedexcompose.deeplink.DeepLinkDelegate
import cardosofgui.android.pokedexcompose.feature.details.navigation.DetailsDeeplinkModuleRegistry
import cardosofgui.android.pokedexcompose.feature.pokemons.navigation.PokemonsDeeplinkModuleRegistry
import cardosofgui.android.pokedexcompose.navigation.AppDeeplinkNavigationImpl
import org.koin.dsl.module

val navigationModules = module {
    factory<AppDeeplinkNavigation>{
        val deeplinkDelegate = DeepLinkDelegate(
            PokemonsDeeplinkModuleRegistry(),
            DetailsDeeplinkModuleRegistry()
        )

        AppDeeplinkNavigationImpl(
            deepLinkDelegate = deeplinkDelegate
        )
    }
}
