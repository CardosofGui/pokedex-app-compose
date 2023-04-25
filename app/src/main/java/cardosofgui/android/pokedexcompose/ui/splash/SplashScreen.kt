package cardosofgui.android.pokedexcompose.ui.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cardosofgui.android.core.components.ui.LottieAnim
import cardosofgui.android.pokedexcompose.R

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        LottieAnim(
            rawRes = R.raw.pikachu_loading,
            modifier = Modifier
                .align(Alignment.Center)
                .size(350.dp)
        )
    }
}