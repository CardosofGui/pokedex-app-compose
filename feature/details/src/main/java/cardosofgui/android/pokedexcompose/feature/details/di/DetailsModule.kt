package cardosofgui.android.pokedexcompose.feature.details.di

import cardosofgui.android.pokedexcompose.feature.details.ui.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsModule = module {
    viewModel { (pokemonId:  Long) ->
        DetailsViewModel(
            getPokemonUseCase = get(),
            favoritePokemonUseCase = get(),
            pokemonId = pokemonId
        )
    }
}