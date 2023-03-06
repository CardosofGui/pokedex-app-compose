package cardosofgui.android.core.components.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BottomSheetShip(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f),
    height: Dp = 5.dp,
    width: Dp = 58.dp,
) {
    Spacer(
        modifier = modifier
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(height.div(2)))
            .background(backgroundColor)
    )
}