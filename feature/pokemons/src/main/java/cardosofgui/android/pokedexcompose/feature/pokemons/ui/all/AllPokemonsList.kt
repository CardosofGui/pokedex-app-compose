package cardosofgui.android.pokedexcompose.feature.pokemons.ui.all

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cardosofgui.android.core.components.ui.LottieAnim
import cardosofgui.android.pokedexcompose.feature.pokemons.R
import cardosofgui.android.pokedexcompose.feature.pokemons.ui.components.PokemonCard
import cardosofgui.android.pokedexcompose.feature.pokemons.ui.main.PokemonsActivity

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun PokemonsActivity.AllPokemonsList(
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val searchLoading = state.searchLoading

    val pokemonList =
        remember(
            state.hasFilterPokemon,
            state.pokemonList,
            state.filterPokemonList
        ) {
            if(state.hasFilterPokemon) state.filterPokemonList
            else state.pokemonList
        }

    AnimatedVisibility(
        visible = searchLoading.not(),
        enter = slideInHorizontally(),
        exit = slideOutHorizontally() + shrinkHorizontally(),
        modifier = modifier
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(128.dp),
            contentPadding = PaddingValues(
                top = 8.dp,
                start = 8.dp,
                end = 8.dp,
                bottom = 8.dp
            ),
            content = {
                itemsIndexed(pokemonList) { index, pokemon ->
                    PokemonCard(
                        pokemon = pokemon,
                        modifier = Modifier.padding(
                            4.dp
                        ),
                        onClickPokemon = { viewModel.clickedPokemon(it) }
                    )

                    DisposableEffect(Unit) {
                        if (index == pokemonList.lastIndex - 10 && !state.hasFilterPokemon)
                            viewModel.loadMore()

                        onDispose {}
                    }
                }
            }
        )
    }


    AnimatedVisibility(
        visible = searchLoading,
        enter = slideInHorizontally(),
        exit = slideOutHorizontally() + shrinkHorizontally(),
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            LottieAnim(
                R.raw.loading_pokeball,
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                text = "Buscando...",
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
            )
        }
    }
}