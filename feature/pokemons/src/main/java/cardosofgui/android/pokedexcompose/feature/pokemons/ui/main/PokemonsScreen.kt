package cardosofgui.android.pokedexcompose.feature.pokemons.ui.main

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import cardosofgui.android.pokedexcompose.feature.pokemons.R
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cardosofgui.android.pokedexcompose.feature.pokemons.ui.all.AllPokemonsList

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun PokemonsActivity.PokemonsScreen(
    modifier: Modifier = Modifier
) {
    val searchPokemon by viewModel.searchPokemon.collectAsStateWithLifecycle()

    Column(modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Icon(
                imageVector = ImageVector
                    .vectorResource(
                        id = R.drawable.pokeball
                    ),
                contentDescription = "Pokeball",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .size(24.dp)
            )

            Text(
                text = "Pokédex",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 16.dp)
            )
        }

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

        AllPokemonsList()
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