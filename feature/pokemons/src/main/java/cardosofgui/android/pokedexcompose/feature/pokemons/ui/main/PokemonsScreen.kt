package cardosofgui.android.pokedexcompose.feature.pokemons.ui.main

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cardosofgui.android.core.components.ui.LottieAnim
import cardosofgui.android.pokedexcompose.feature.pokemons.ui.components.PokemonCard
import cardosofgui.android.pokedexcompose.feature.pokemons.R

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun PokemonsActivity.PokemonsScreen(
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val searchPokemon by viewModel.searchPokemon.collectAsStateWithLifecycle()
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

    Column(modifier) {
        RoundedTextField(
            value = searchPokemon,
            onValueChange = { search -> viewModel.updateSearchPokemon(search) },
            placeholder = {
                Text(
                    text = "Pesquisar",
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        AnimatedVisibility(
            visible = searchLoading.not(),
            enter = slideInHorizontally(),
            exit = slideOutHorizontally() + shrinkHorizontally()
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
            exit = slideOutHorizontally() + shrinkHorizontally()
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RoundedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    strokeColor: Color = MaterialTheme.colorScheme.primary,
    placeholder: @Composable (() -> Unit)? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .border(2.dp, strokeColor, RoundedCornerShape(20.dp))
            .heightIn(min = 35.dp)
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            placeholder = placeholder
        )

        AnimatedVisibility(
            visible = value.isNotEmpty(),
            enter = slideInHorizontally(),
            exit = slideOutHorizontally() + shrinkHorizontally()
        ) {
            IconButton(
                onClick = {
                    onValueChange("")
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = "Remove icon",
                    tint = strokeColor,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}