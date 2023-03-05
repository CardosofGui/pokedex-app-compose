package cardosofgui.android.pokedexcompose.feature.pokemons.ui.state

import cardosofgui.android.pokedexcompose.core.network.model.Pokemon

data class PokemonsState(
    val isLoading: Boolean = true,
    val limit: Long = 20,
    val offset: Long = 0,
    val pokemonList: List<Pokemon> = emptyList()
)
