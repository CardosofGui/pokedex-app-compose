package cardosofgui.android.pokedexcompose.feature.pokemons.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cardosofgui.android.core.components.ui.LottieAnim
import cardosofgui.android.pokedexcompose.core.network.model.Pokemon
import cardosofgui.android.pokedexcompose.core.network.model.PokemonTypes
import cardosofgui.android.pokedexcompose.feature.pokemons.R
import coil.compose.SubcomposeAsyncImage
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PokemonCard(
    modifier: Modifier = Modifier,
    pokemon: Pokemon,
    onClickPokemon: (Long) -> Unit
) {
    Card(
        onClick = { onClickPokemon(pokemon.id ?: 0) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(128.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painter = painterResource(id = R.drawable.pokeicon),
                    contentScale = ContentScale.Fit,
                    alpha = if (isSystemInDarkTheme()) 0.3f else 0.05f
                )
                .padding(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            var pokemonImage by remember { mutableStateOf(pokemon.mainImage) }

            SubcomposeAsyncImage(
                model = pokemonImage,
                contentDescription = "",
                contentScale = ContentScale.Fit,
                loading = {
                    LottieAnim(rawRes = R.raw.pokemon_loading)
                },
                error = {
                    LaunchedEffect(Unit) {
                        pokemonImage = pokemon.secondaryImage
                    }
                },
                modifier = Modifier.size(72.dp)
            )

            Text(
                text = pokemon.name?.capitalize(Locale.ROOT).orEmpty(),
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )

            Text(
                text = "#${pokemon.id.toString().padStart(3, '0')}",
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp
            )
        }
    }
}