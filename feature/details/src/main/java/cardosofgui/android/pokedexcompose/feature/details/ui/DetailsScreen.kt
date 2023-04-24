package cardosofgui.android.pokedexcompose.feature.details.ui

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cardosofgui.android.core.components.ui.BackButton
import cardosofgui.android.core.components.ui.LottieAnim
import cardosofgui.android.core.components.utils.Extensions.Companion.getBackgroundColor
import cardosofgui.android.pokedexcompose.core.network.model.Stats
import cardosofgui.android.pokedexcompose.core.network.model.Types
import cardosofgui.android.pokedexcompose.feature.details.R
import coil.compose.SubcomposeAsyncImage
import java.util.Locale

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
    val favoriteStatus = pokemonDetails?.favoriteStatus ?: false
    val mainTypeColor = pokemonDetails?.types?.first()?.type?.name.getBackgroundColor()

    window.statusBarColor = mainTypeColor.toArgb()

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .background(mainTypeColor)
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.pokeball),
                contentScale = ContentScale.Fit,
                alpha = if (isSystemInDarkTheme()) 0.15f else 0.15f,
                alignment = TopEnd
            )
    ) {
        Box {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                BackButton(
                    onClickBack = { finish() },
                    modifier = Modifier.padding(6.dp)
                )

                Text(
                    text = pokemonDetails?.name?.capitalize(Locale.ROOT).orEmpty(),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.weight(1f)
                )
                
                Text(
                    text = if(pokemonId != null) "#${pokemonDetails.id.toString().padStart(3, '0')}" else "Carregando...",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(end = 18.dp)
                )
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            SubcomposeAsyncImage(
                model = pokemonDetails?.mainImage,
                contentDescription = "",
                contentScale = ContentScale.Fit,
                loading = {
                    LottieAnim(rawRes = cardosofgui.android.core.components.R.raw.pokemon_loading)
                },
                modifier = Modifier
                    .size(284.dp)
                    .align(TopCenter)
                    .zIndex(1f)
            )


            Box(
                modifier = Modifier
                    .align(BottomCenter)
                    .fillMaxWidth()
                    .fillMaxHeight(0.68f)
                    .padding(4.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
            ) {
                Crossfade(
                    targetState = favoriteStatus,
                    modifier = Modifier
                        .align(TopEnd)

                ) {
                    when(it) {
                        true -> FavoriteIcon(
                            favoriteStatus = true,
                            onClickFavorite = { viewModel.favoritePokemon(false) },
                            modifier = Modifier
                                .padding(18.dp)
                                .size(32.dp)
                        )
                        false -> FavoriteIcon(
                            favoriteStatus = false,
                            onClickFavorite = { viewModel.favoritePokemon(true) },
                            modifier = Modifier
                                .padding(18.dp)
                                .size(32.dp)
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .padding(top = 64.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
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

                    Text(
                        text = "Sobre",
                        fontSize = 24.sp,
                        color = mainTypeColor,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(CenterHorizontally)
                            .padding(top = 12.dp)
                    )

                    Row {
                        AboutItem(
                            label = "Altura",
                            value = "${pokemonDetails?.height ?: 0.0} m",
                            icon = ImageVector.vectorResource(id = R.drawable.height),
                            modifier = Modifier
                                .padding(12.dp)
                                .weight(1f)
                        )

                        AboutItem(
                            label = "Peso",
                            value = "${pokemonDetails?.weight ?: 0.0} kg",
                            icon = ImageVector.vectorResource(id = R.drawable.weight),
                            modifier = Modifier
                                .padding(12.dp)
                                .weight(1f)
                        )
                    }

                    Text(
                        text = "Status Base",
                        fontSize = 24.sp,
                        color = mainTypeColor,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(CenterHorizontally)
                            .padding(vertical = 12.dp)
                    )


                    Column {
                        pokemonDetails?.stats?.forEach { stat ->
                            StatLineWithLabel(
                                stat = stat,
                                color = mainTypeColor,
                                modifier = Modifier.padding(horizontal = 12.dp)
                            )
                        }
                    }
                }
            }
        }
    }


    LaunchedEffect(Unit) {
        viewModel.action.collect(::handleActions)
    }
}

@Composable
private fun FavoriteIcon(
    favoriteStatus: Boolean,
    onClickFavorite: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClickFavorite,
        modifier = modifier
    ) {
        Icon(
            imageVector = if(favoriteStatus) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = "Favorite Icon",
            tint = Color.Red
        )
    }
}

@Composable
private fun AboutItem(
    label: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "$label icon",
                tint = Color.Black,
                modifier = Modifier
                    .size(16.dp)
                    .padding(end = 4.dp)
            )

            Text(
                text = value,
                fontSize = 14.sp,
                color = Color.Black
            )
        }

        Text(
            text = label,
            fontSize = 12.sp,
            modifier = Modifier
                .align(CenterHorizontally)
        )
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
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary
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
            color = color
        )

        StatLinearProgress(
            baseStatFloat = stat.calculateProgress(),
            color = color,
            modifier = Modifier
                .padding(vertical = 6.dp)
                .height(14.dp)
                .weight(1f)
                .padding(horizontal = 6.dp)
                .clip(CircleShape)
        )

        Text(
            text = baseStat.padStart(3, '0'),
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = Color.Black
        )
    }
}

@Composable
fun StatLinearProgress(
    baseStatFloat: Float,
    modifier: Modifier = Modifier,
    color: Color,
) {
    var progress by remember { mutableStateOf(0f) }
    val progressAnimDuration = 1500
    val progressAnimation by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = progressAnimDuration, easing = FastOutSlowInEasing)
    )

    LinearProgressIndicator(
        progress = progressAnimation,
        color = color,
        trackColor = color.copy(.4f),
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
        text = type.type.name?.name?.capitalize(Locale.ROOT).orEmpty(),
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = modifier
            .clip(RoundedCornerShape(40.dp))
            .background(type.type.name.getBackgroundColor())
            .padding(horizontal = 12.dp, vertical = 4.dp)
    )
}