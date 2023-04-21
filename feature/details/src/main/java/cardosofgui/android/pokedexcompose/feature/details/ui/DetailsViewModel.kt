package cardosofgui.android.pokedexcompose.feature.details.ui

import androidx.lifecycle.viewModelScope
import cardosofgui.android.core.components.utils.ViewModel
import cardosofgui.android.pokedexcompose.core.usecase.FavoritePokemonUseCase
import cardosofgui.android.pokedexcompose.core.usecase.GetPokemonUseCase
import cardosofgui.android.pokedexcompose.feature.details.ui.state.DetailsAction
import cardosofgui.android.pokedexcompose.feature.details.ui.state.DetailsState
import kotlinx.coroutines.launch
import java.util.Locale

class DetailsViewModel(
    private val getPokemonUseCase: GetPokemonUseCase,
    private val favoritePokemonUseCase: FavoritePokemonUseCase,
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

    fun favoritePokemon() {
        viewModelScope.launch {
            try {
                val currentPokemon = state.value.pokemonDetails ?: return@launch
                val pokemonName = currentPokemon.name?.capitalize(Locale.ROOT)

                val favoriteStatus = if(currentPokemon.favoriteStatus) {
                    favoritePokemonUseCase.removeFavoritePokemon(currentPokemon)
                } else {
                    favoritePokemonUseCase.addFavoritePokemon(currentPokemon)
                }

                setState(
                    state.value.copy(
                        pokemonDetails = currentPokemon.copy(
                            favoriteStatus = favoriteStatus
                        )
                    )
                )

                sendAction(
                    DetailsAction.ShowToast(
                        if(favoriteStatus) "$pokemonName adicionado aos favoritos" else "$pokemonName removido dos favoritos"
                    )
                )
            } catch (e: Exception) {
                // Todo DEU RUIM
            }
        }
    }
}