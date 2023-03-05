package cardosofgui.android.pokedexcompose.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cardosofgui.android.pokedexcompose.core.usecase.GetPokemonUseCase
import cardosofgui.android.pokedexcompose.core.network.model.Pokemon
import kotlinx.coroutines.launch

class ListViewModel(
    private val getPokemonUseCase: GetPokemonUseCase,
) : ViewModel() {

    var pokemonSelected by mutableStateOf(Pokemon())
        private set

    init {
        viewModelScope.launch {
            val response = getPokemonUseCase.getPokemonByName("")

            if(response != null) {
                pokemonSelected = response
            }
        }
    }
}