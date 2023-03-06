package cardosofgui.android.pokedexcompose.feature.pokemons.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cardosofgui.android.core.components.ui.LottieAnim
import cardosofgui.android.pokedexcompose.feature.pokemons.ui.components.PokemonCard
import cardosofgui.android.pokedexcompose.feature.pokemons.R

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun PokemonsActivity.PokemonsScreen(
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(modifier) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(128.dp),
            contentPadding = PaddingValues(
                top = 8.dp,
                start = 8.dp,
                end = 8.dp,
                bottom = 8.dp
            ),
            content = {
                itemsIndexed(state.pokemonList) { index, pokemon ->
                    PokemonCard(
                        pokemon = pokemon,
                        modifier = Modifier.padding(
                            4.dp
                        ),
                        onClickPokemon = { viewModel.clickedPokemon(it) }
                    )

                    DisposableEffect(Unit) {
                        if (index == state.pokemonList.lastIndex - 10)
                            viewModel.loadMore()

                        onDispose {}
                    }
                }
            }
        )
    }
}