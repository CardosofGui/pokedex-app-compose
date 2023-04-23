package cardosofgui.android.pokedexcompose.feature.pokemons.ui.state

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import cardosofgui.android.core.components.utils.UIState
import cardosofgui.android.pokedexcompose.core.network.model.Pokemon
import cardosofgui.android.pokedexcompose.feature.pokemons.R

data class PokemonsState(
    val isLoading: Boolean = true,
    val limit: Long = 10000,
    val offset: Long = 0,
    val pokemonList: List<Pokemon> = emptyList(),
    val filterPokemonList: List<Pokemon> = emptyList(),
    val searchLoading: Boolean = false,
    val hasFilterPokemon: Boolean = false
): UIState