package cardosofgui.android.pokedexcompose.feature.pokemons.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cardosofgui.android.pokedexcompose.core.network.model.FilterType
import cardosofgui.android.pokedexcompose.feature.pokemons.R
import cardosofgui.android.pokedexcompose.feature.pokemons.ui.all.AllPokemonsList
import cardosofgui.android.pokedexcompose.feature.pokemons.ui.components.BottomSheetFilter
import kotlinx.coroutines.launch

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalMaterialApi::class)
@Composable
internal fun PokemonsActivity.PokemonsScreen(
    modifier: Modifier = Modifier
) {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val searchPokemon by viewModel.searchPokemon.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            BottomSheetFilter(
                onFilterSelected = { filter ->
                    sheetState.hide()
                    viewModel.updateFilterType(filter)
                }
            )
        },
        sheetShape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp
        ),
    ) {
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

            Row(
                Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RoundedTextField(
                    value = searchPokemon,
                    onValueChange = { search -> viewModel.updateSearchPokemon(search) },
                    placeholder = {
                        Text(
                            text = "Pesquisar",
                            color = Color.Black.copy(alpha = 0.5f),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal
                        )
                    },
                    modifier = Modifier
                        .height(55.dp)
                        .weight(1f)
                )

                FilterButton(
                    selectedFilter = FilterType.FAVORITE,
                    onClick = {
                        scope.launch {
                            sheetState.show()
                        }
                    },
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clip(CircleShape)
                )
            }

            AllPokemonsList()
        }
    }
}

@Composable
fun FilterButton(
    selectedFilter: FilterType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary)
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Icon(
                imageVector = selectedFilter.getIcon(),
                contentDescription = "Filter icon",
                tint = MaterialTheme.colorScheme.background,
                modifier = Modifier.size(24.dp)
            )
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
            .border(2.dp, strokeColor, RoundedCornerShape(42.dp))
            .background(MaterialTheme.colorScheme.onBackground, RoundedCornerShape(42.dp))
    ) {
        Icon(
            imageVector = Icons.Outlined.Search,
            contentDescription = "Remove icon",
            tint = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .padding(start = 16.dp)
                .size(24.dp)
        )

        TextField(
            value = value,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                textColor = Color.Black
            ),
            placeholder = placeholder
        )
    }
}

@Composable
fun FilterType.getIcon() = when (this) {
    FilterType.NUMBER -> ImageVector.vectorResource(id = R.drawable.number)
    FilterType.NAME -> ImageVector.vectorResource(id = R.drawable.text)
    FilterType.FAVORITE -> Icons.Outlined.Favorite
}

fun FilterType.getLabel() = when (this) {
    FilterType.NUMBER -> "Ordenar por Número"
    FilterType.NAME -> "Ordenar por Nome"
    FilterType.FAVORITE -> "Meus Favoritos"
}