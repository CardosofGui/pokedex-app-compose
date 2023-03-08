package cardosofgui.android.core.components.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieAnim(
    rawRes: Int,
    modifier: Modifier = Modifier,
){
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(rawRes)
    )

    val lottieProgress by animateLottieCompositionAsState(composition = composition, iterations = LottieConstants.IterateForever )

    LottieAnimation(
        composition = composition,
        modifier = modifier,
        progress = { lottieProgress }
    )

}