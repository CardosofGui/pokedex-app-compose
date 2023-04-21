package cardosofgui.android.pokedexcompose.core.data.di

import cardosofgui.android.pokedexcompose.core.usecase.FavoritePokemonUseCase
import cardosofgui.android.pokedexcompose.core.usecase.GetPokemonUseCase
import org.koin.dsl.module

val useCaseModules = module {
    factory {
        GetPokemonUseCase(
            pokemonRepository = get()
        )
    }

    factory {
        FavoritePokemonUseCase(
            pokemonRepository = get()
        )
    }
}