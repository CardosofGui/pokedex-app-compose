package cardosofgui.android.pokedexcompose.feature.pokemons.ui.state

import cardosofgui.android.core.components.utils.UIState
import cardosofgui.android.pokedexcompose.core.network.model.FilterType
import cardosofgui.android.pokedexcompose.core.network.model.Pokemon

data class PokemonsState(
    val isLoading: Boolean = true,
    val limit: Long = 10000,
    val offset: Long = 0,
    val allPokemonList: List<Pokemon> = emptyList(),
    val pokemonList: List<Pokemon> = emptyList(),
    val filterPokemonList: List<Pokemon> = emptyList(),
    val searchLoading: Boolean = false,
    val filterType: FilterType? = FilterType.NUMBER,
    val hasFilterPokemon: Boolean = false
): UIState