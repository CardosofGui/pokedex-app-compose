package cardosofgui.android.pokedexcompose.feature.details.ui

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cardosofgui.android.core.components.R
import cardosofgui.android.core.components.ui.LottieAnim
import cardosofgui.android.core.components.utils.Extensions.Companion.getBackgroundColor
import cardosofgui.android.pokedexcompose.core.network.model.Stat
import cardosofgui.android.pokedexcompose.core.network.model.Stats
import coil.compose.SubcomposeAsyncImage

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun DetailsMainActivity.DetailsScreen(
    viewModel: DetailsViewModel,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val pokemonDetails = state.pokemonDetails

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .paint(
                painter = painterResource(id = R.drawable.pokeicon),
                contentScale = ContentScale.FillHeight,
                alpha = if (isSystemInDarkTheme()) 0.07f else 0.04f,
                alignment = Alignment.TopCenter
            )
    ) {
        SubcomposeAsyncImage(
            model = pokemonDetails?.mainImage,
            contentDescription = "",
            contentScale = ContentScale.Fit,
            loading = {
                LottieAnim(rawRes = R.raw.pokemon_loading)
            },
            modifier = Modifier
                .size(252.dp)
                .align(CenterHorizontally)
                .weight(1f)
                .fillMaxWidth()
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 256.dp)
                .clip(RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp))
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = pokemonDetails?.name.orEmpty(),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "#${pokemonDetails?.id.toString().padStart(3, '0')}",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground.copy(.6f),
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

fun calculateProgress(
    stat: Stats
): Float {
    val result = ((stat.baseStat ?: 0) * 100) / 255
    return result.toFloat()
}