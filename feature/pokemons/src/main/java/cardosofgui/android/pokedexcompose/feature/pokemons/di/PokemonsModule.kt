package cardosofgui.android.pokedexcompose.feature.pokemons.di

import cardosofgui.android.pokedexcompose.feature.pokemons.ui.main.PokemonsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pokemonsModule = module {
    viewModel {
        PokemonsViewModel(
            getPokemonUseCase = get()
        )
    }
}