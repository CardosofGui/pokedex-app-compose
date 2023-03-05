package cardosofgui.android.pokedexcompose.di

import cardosofgui.android.pokedexcompose.ui.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    viewModel {
        ListViewModel(
            getPokemonUseCase = get()
        )
    }
}

