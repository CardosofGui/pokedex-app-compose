package cardosofgui.android.core.components.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieAnim(
    rawRes: Int,
    modifier: Modifier = Modifier,
){
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(rawRes))
    LottieAnimation(
        composition = composition,
        modifier = modifier
    )

}