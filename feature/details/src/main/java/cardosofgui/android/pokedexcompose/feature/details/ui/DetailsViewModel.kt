package cardosofgui.android.pokedexcompose.feature.details.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import cardosofgui.android.core.components.utils.ViewModel
import cardosofgui.android.pokedexcompose.core.usecase.FavoritePokemonUseCase
import cardosofgui.android.pokedexcompose.core.usecase.GetPokemonUseCase
import cardosofgui.android.pokedexcompose.feature.details.ui.state.DetailsAction
import cardosofgui.android.pokedexcompose.feature.details.ui.state.DetailsState
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.Locale

class DetailsViewModel(
    private val getPokemonUseCase: GetPokemonUseCase,
    private val favoritePokemonUseCase: FavoritePokemonUseCase,
    var pokemonId: Long
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
                getPokemonUseCase.getPokemonById(pokemonId).collect {
                    setState(
                        state.value.copy(
                            pokemonDetails = it
                        )
                    )
                }
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

    fun favoritePokemon(favorite: Boolean) {
        viewModelScope.launch {
            try {
                val currentPokemon = state.value.pokemonDetails ?: return@launch
                val pokemonName = currentPokemon.name?.capitalize(Locale.ROOT)

                if(favorite) {
                    favoritePokemonUseCase.addFavoritePokemon(currentPokemon)
                } else {
                    favoritePokemonUseCase.removeFavoritePokemon(currentPokemon)
                }

                setState(
                    state.value.copy(
                        pokemonDetails = currentPokemon.copy(
                            favoriteStatus = favorite
                        )
                    )
                )

                sendAction(
                    DetailsAction.ShowToast(
                        if(favorite) "$pokemonName adicionado aos favoritos" else "$pokemonName removido dos favoritos"
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}