package cardosofgui.android.pokedexcompose.feature.pokemons.ui.main

import android.util.Log
import androidx.lifecycle.viewModelScope
import cardosofgui.android.core.components.utils.ViewModel
import cardosofgui.android.pokedexcompose.core.usecase.GetPokemonUseCase
import cardosofgui.android.pokedexcompose.feature.pokemons.ui.state.PokemonsAction
import cardosofgui.android.pokedexcompose.feature.pokemons.ui.state.PokemonsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PokemonsViewModel(
    private val getPokemonUseCase: GetPokemonUseCase
): ViewModel<PokemonsState, PokemonsAction>(PokemonsState()) {

    init {
        getPokemonList()
    }

    private fun getPokemonList() {
        viewModelScope.launch {
            state.value = state.value.copy(
                isLoading = true
            )

            try {
                val stateValues = state.value

                val pokemonList = getPokemonUseCase.getPokemonList(
                    limit = stateValues.limit,
                    offset = stateValues.offset * stateValues.limit
                ) ?: emptyList()

                state.value = state.value.copy(
                    pokemonList = (stateValues.pokemonList + pokemonList).distinct()
                )

                Log.e("PokemonsViewModel", "Cheguei aqui! Deu bom $pokemonList")

            } catch (e: Exception) {
                Log.e("PokemonsViewModel", e.message.orEmpty())

                // TODO ADICIONAR TRATATIVA DE ERRO
            } finally {
                state.value = state.value.copy(
                    isLoading = false
                )
            }
        }
    }

    fun loadMore() {
        viewModelScope.launch {
            val stateValues = state.value

            state.value = state.value.copy(
                offset = stateValues.offset+1,
            )

            getPokemonList()
        }
    }

    fun clickedPokemon(
        pokemonId: Long
    ) {
        viewModelScope.launch {
            sendAction(
                PokemonsAction.OpenPokemonDetails(pokemonId)
            )
        }
    }
}