package cardosofgui.android.pokedexcompose.startup

import android.content.Context
import androidx.startup.Initializer
import cardosofgui.android.pokedexcompose.core.data.di.databaseModules
import cardosofgui.android.pokedexcompose.core.data.di.errorModules
import cardosofgui.android.pokedexcompose.core.data.di.networkModules
import cardosofgui.android.pokedexcompose.core.data.di.repositoryModules
import cardosofgui.android.pokedexcompose.core.data.di.useCaseModules
import cardosofgui.android.pokedexcompose.di.appModules
import cardosofgui.android.pokedexcompose.di.navigationModules
import cardosofgui.android.pokedexcompose.feature.details.di.detailsModule
import cardosofgui.android.pokedexcompose.feature.pokemons.di.pokemonsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

class KoinInitializer : Initializer<KoinApplication> {
    override fun create(context: Context): KoinApplication {
        val modules = mutableListOf(
            databaseModules,
            navigationModules,
            errorModules,
            networkModules,
            repositoryModules,
            useCaseModules,
            detailsModule,
            pokemonsModule,
            appModules
        )

        return startKoin {
            androidContext(context)
            modules(modules)
            allowOverride(true)
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}