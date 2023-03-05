package cardosofgui.android.pokedexcompose.feature.details.ui

import androidx.lifecycle.viewModelScope
import cardosofgui.android.core.components.utils.ViewModel
import cardosofgui.android.pokedexcompose.core.usecase.GetPokemonUseCase
import cardosofgui.android.pokedexcompose.feature.details.ui.state.DetailsAction
import cardosofgui.android.pokedexcompose.feature.details.ui.state.DetailsState
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val getPokemonUseCase: GetPokemonUseCase,
    val pokemonId: Long
): ViewModel<DetailsState, DetailsAction>(DetailsState()) {
    init {
        getPokemonDetails()
    }

    private fun getPokemonDetails() {
        viewModelScope.launch {
            setState(
                state.value.copy(
                    isLoading = true
                )
            )

            try {
                val response = getPokemonUseCase.getPokemonById(pokemonId)

                setState(
                    state.value.copy(
                        pokemonDetails = response
                    )
                )
            } catch (e: Exception) {
                // TODO DEU RUIM
            } finally {
                setState(
                    state.value.copy(
                        isLoading = false
                    )
                )
            }
        }
    }
}