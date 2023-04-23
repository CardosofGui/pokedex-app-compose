package cardosofgui.android.pokedexcompose.feature.pokemons.ui.main

import android.util.Log
import androidx.lifecycle.viewModelScope
import cardosofgui.android.core.components.utils.ViewModel
import cardosofgui.android.pokedexcompose.core.network.model.FilterType
import cardosofgui.android.pokedexcompose.core.network.model.UserSettings
import cardosofgui.android.pokedexcompose.core.usecase.GetPokemonUseCase
import cardosofgui.android.pokedexcompose.core.usecase.GetUserUseCase
import cardosofgui.android.pokedexcompose.feature.pokemons.ui.state.PokemonsAction
import cardosofgui.android.pokedexcompose.feature.pokemons.ui.state.PokemonsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class PokemonsViewModel(
    private val getPokemonUseCase: GetPokemonUseCase,
    private val getUserUseCase: GetUserUseCase
): ViewModel<PokemonsState, PokemonsAction>(PokemonsState()) {

    var searchPokemon = MutableStateFlow("")

    init {
        collectSearchPokemon()
        getPokemonList()
        collectUser()
    }

    private fun collectUser() {
        viewModelScope.launch {
            getUserUseCase().collect {
                setState(
                    state.value.copy(
                        filterType = it?.filterType
                    )
                )

                fetchPokemonListWithFilter()
            }
        }
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
                        allPokemonList = it
                    )

                    fetchPokemonListWithFilter()
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

                if(searchValue.isNotEmpty()) {
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
                }
                else {
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

    fun updateFilterType(
        filterType: FilterType
    ) {
        viewModelScope.launch {
            getUserUseCase.updateUser(
                UserSettings(
                    filterType = filterType
                )
            )
        }
    }

    private fun fetchPokemonListWithFilter() {
        val actualFilter = state.value.filterType

        viewModelScope.launch {
            when(actualFilter) {
                FilterType.NUMBER -> {
                    setState(
                        state.value.copy(
                            pokemonList = state.value.allPokemonList.sortedBy { pokemon ->
                                pokemon.id
                            }
                        )
                    )
                }
                FilterType.NAME -> {
                    setState(
                        state.value.copy(
                            pokemonList = state.value.allPokemonList.sortedBy { pokemon ->
                                pokemon.name
                            }
                        )
                    )
                }
                FilterType.FAVORITE -> {
                    setState(
                        state.value.copy(
                            pokemonList = state.value.allPokemonList.filter { pokemon ->
                                pokemon.favoriteStatus
                            }
                        )
                    )
                }

                else -> {}
            }
        }
    }
}