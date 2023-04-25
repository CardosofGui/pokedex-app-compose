package cardosofgui.android.pokedexcompose.feature.pokemons.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cardosofgui.android.core.components.ui.BottomSheetShip
import cardosofgui.android.pokedexcompose.core.network.model.FilterType
import cardosofgui.android.pokedexcompose.feature.pokemons.ui.main.getIcon
import cardosofgui.android.pokedexcompose.feature.pokemons.ui.main.getLabel
import kotlinx.coroutines.launch

@Composable
fun BottomSheetFilter(
    onFilterSelected: suspend (FilterType) -> Unit,
    modifier: Modifier = Modifier
) {
    val filterList = listOf(
        FilterType.FAVORITE,
        FilterType.NAME,
        FilterType.NUMBER
    )

    val scope = rememberCoroutineScope()

    Column(
        modifier
    ) {
        BottomSheetShip(
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(top = 8.dp)
        )

        Text(
            text = "Filtros",
            fontSize = 24.sp,
            modifier = Modifier
                .padding(top = 16.dp)
                .align(CenterHorizontally)
        )

        filterList.forEach { filter ->
            FilterItem(
                filter = filter,
                onFilterSelected = {
                    scope.launch {
                        onFilterSelected(filter)
                    }
                },
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterItem(
    filter: FilterType,
    onFilterSelected: (FilterType) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(16.dp),
        onClick = {
            onFilterSelected(filter)
        },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
        ),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.8f))
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = filter.getIcon(),
                    contentDescription = "${filter.getLabel()} Icon",
                    tint = MaterialTheme.colorScheme.background,
                    modifier = Modifier
                        .padding(16.dp)
                        .size(24.dp)
                )

                Text(
                    text = filter.getLabel(),
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
            }
        }
    }
}
