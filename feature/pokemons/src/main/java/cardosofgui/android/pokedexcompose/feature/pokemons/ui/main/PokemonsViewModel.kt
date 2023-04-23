package cardosofgui.android.pokedexcompose.feature.pokemons.ui.main

import android.util.Log
import androidx.lifecycle.viewModelScope
import cardosofgui.android.core.components.utils.ViewModel
import cardosofgui.android.pokedexcompose.core.network.model.User
import cardosofgui.android.pokedexcompose.core.usecase.GetPokemonUseCase
import cardosofgui.android.pokedexcompose.core.usecase.GetUserUseCase
import cardosofgui.android.pokedexcompose.feature.pokemons.ui.state.PokemonNavigation
import cardosofgui.android.pokedexcompose.feature.pokemons.ui.state.PokemonsAction
import cardosofgui.android.pokedexcompose.feature.pokemons.ui.state.PokemonsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class PokemonsViewModel(
    private val getPokemonUseCase: GetPokemonUseCase
): ViewModel<PokemonsState, PokemonsAction>(PokemonsState()) {

    var searchPokemon = MutableStateFlow("")

    init {
        collectSearchPokemon()
        collectFavoritePokemonList()
        getPokemonList()
    }

    private fun getPokemonList() {
        viewModelScope.launch {
            state.value = state.value.copy(
                isLoading = true
            )

            try {
                val stateValues = state.value

                getPokemonUseCase.getPokemonList(
                    limit = stateValues.limit,
                    offset = stateValues.offset * stateValues.limit
                ).collect {
                    state.value = state.value.copy(
                        pokemonList = it
                    )
                }
            } catch (e: Exception) {
                Log.e("PokemonsViewModel", e.message.orEmpty())
            } finally {
                state.value = state.value.copy(
                    isLoading = false
                )
            }
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

    private fun collectSearchPokemon() {
        viewModelScope.launch {
            searchPokemon.debounce(2500).collect {
                val searchValue = searchPokemon.value
                val navigation = state.value.navigation

                if(searchValue.isNotEmpty()) {
                    if(navigation is PokemonNavigation.AllPokemonList) {
                        setState(
                            state.value.copy(
                                filterPokemonList = state.value.pokemonList.filter { pokemon ->
                                    pokemon.name.orEmpty().contains(searchValue, true) ||
                                    pokemon.id.toString().contains(searchValue, true)
                                },
                                hasFilterPokemon = true,
                                searchLoading = false
                            )
                        )
                    } else if(navigation is PokemonNavigation.FavoritesPokemonList) {
                        setState(
                            state.value.copy(
                                filterFavoritePokemonList = state.value.favoritePokemonList.filter { pokemon ->
                                    pokemon.name.orEmpty().contains(searchValue, true) ||
                                    pokemon.id.toString().contains(searchValue, true)
                                },
                                hasFilterPokemon = true,
                                searchLoading = false
                            )
                        )
                    }
                } else {
                    setState(
                        state.value.copy(
                            filterPokemonList = emptyList(),
                            hasFilterPokemon = false,
                            searchLoading = false
                        )
                    )
                }
            }
        }
    }

    fun updateSearchPokemon(
        pokemonName: String
    ) {
        viewModelScope.launch {
            setState(
                state.value.copy(
                    searchLoading = true
                )
            )

            searchPokemon.value = pokemonName
        }
    }

    fun updateNavigation(
        navigation: PokemonNavigation
    ) {
        viewModelScope.launch {
            setState(
                state.value.copy(
                    navigation = navigation,
                    hasFilterPokemon = false,
                    searchLoading = false
                )
            )

            searchPokemon.value = ""
        }
    }

    fun collectFavoritePokemonList() {
        viewModelScope.launch {
            getPokemonUseCase.getFavoritePokemonList().collect { favoritePokemonList ->
                setState(
                    state.value.copy(
                        favoritePokemonList = favoritePokemonList
                    )
                )
            }
        }
    }
}