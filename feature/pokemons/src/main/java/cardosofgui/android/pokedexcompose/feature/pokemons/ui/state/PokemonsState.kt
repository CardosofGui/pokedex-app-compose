package cardosofgui.android.pokedexcompose.feature.pokemons.ui.state

import cardosofgui.android.core.components.utils.UIState
import cardosofgui.android.pokedexcompose.core.network.model.Pokemon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class PokemonsState(
    val isLoading: Boolean = true,
    val limit: Long = 10000,
    val offset: Long = 0,
    val pokemonList: List<Pokemon> = emptyList(),
    val filterPokemonList: List<Pokemon> = emptyList(),
    val hasFilterPokemon: Boolean = false
): UIState
