package cardosofgui.android.pokedexcompose.feature.details.ui

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cardosofgui.android.core.components.R
import cardosofgui.android.core.components.ui.BackButton
import cardosofgui.android.core.components.ui.BottomSheetShip
import cardosofgui.android.core.components.ui.LottieAnim
import cardosofgui.android.core.components.utils.Extensions.Companion.getBackgroundColor
import cardosofgui.android.pokedexcompose.core.network.model.Stat
import cardosofgui.android.pokedexcompose.core.network.model.Stats
import cardosofgui.android.pokedexcompose.core.network.model.Types
import coil.compose.SubcomposeAsyncImage
import java.util.*

@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalAnimationApi::class)
@Composable
internal fun DetailsMainActivity.DetailsScreen(
    viewModel: DetailsViewModel,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val pokemonDetails = state.pokemonDetails

    val pokemonId = pokemonDetails?.id
    val name = pokemonDetails?.name
    val pokemonName = name

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
        Box {
            BackButton(
                onClickBack = { finish() },
                modifier = Modifier.padding(6.dp)
            )
        }

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
                .clip(RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp))
                .background(MaterialTheme.colorScheme.primary)
                .padding(bottom = 12.dp)
        ) {
            BottomSheetShip(
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(vertical = 6.dp)
            )

            Text(
                text = (pokemonName ?: "Carregando...").capitalize(Locale.ROOT),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = if(pokemonId != null) "#${pokemonDetails.id.toString().padStart(3, '0')}" else "Carregando...",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground.copy(.6f),
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                pokemonDetails?.types?.forEach { type ->
                    TypeShip(
                        type = type,
                        modifier = Modifier
                            .padding(horizontal = 6.dp)
                    )
                }
            }

            AnimatedVisibility(
                visible = !state.isLoading,
            ) {
                Column {
                    pokemonDetails?.stats?.forEach { stat ->
                        StatLineWithLabel(
                            stat = stat,
                            modifier = Modifier.padding(horizontal = 12.dp)
                        )
                    }
                }
            }
        }
    }
}

fun Stats?.calculateProgress(): Float {
    if(this == null) return 0f
    val baseStat = this.baseStat?.toFloat() ?: return 0f
    val result = baseStat*100f/255f/100f
    return result
}

@Composable
fun StatLineWithLabel(
    stat: Stats,
    modifier: Modifier = Modifier
) {
    val name = stat.stat?.name.orEmpty().replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
    val baseStat = stat.baseStat.toString()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        StatLinearProgress(
            baseStatFloat = stat.calculateProgress(),
            modifier = Modifier
                .height(10.dp)
                .weight(1f)
                .padding(horizontal = 6.dp)
        )

        Text(
            text = baseStat,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun StatLinearProgress(
    baseStatFloat: Float,
    modifier: Modifier = Modifier
) {
    var progress by remember { mutableStateOf(0f) }
    val progressAnimDuration = 1500
    val progressAnimation by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = progressAnimDuration, easing = FastOutSlowInEasing)
    )

    LinearProgressIndicator(
        progress = progressAnimation,
        color = MaterialTheme.colorScheme.onPrimary,
        trackColor = MaterialTheme.colorScheme.background,
        modifier = modifier
            .fillMaxWidth()
    )

    LaunchedEffect(baseStatFloat) {
        progress = baseStatFloat
    }
}

@Composable
fun TypeShip(
    type: Types,
    modifier: Modifier = Modifier
) {
    Text(
        text = type.type.name?.name.orEmpty(),
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(type.type.name.getBackgroundColor())
            .padding(horizontal = 12.dp, vertical = 4.dp)
    )
}