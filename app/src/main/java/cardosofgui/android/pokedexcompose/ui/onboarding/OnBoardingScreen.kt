package cardosofgui.android.pokedexcompose.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cardosofgui.android.pokedexcompose.R
import cardosofgui.android.pokedexcompose.ui.SplashActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

@Composable
internal fun SplashActivity.OnBoardingScreen(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize().background(Color.White)) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(18.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.onboarding),
                contentDescription = "",
                modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                text = "Todos os Pokémons em um só Lugar",
                fontSize = 26.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                text = "Acesse uma vasta lista de Pokémon de todas as gerações já feitas pela Nintendo",
                fontSize = 14.sp,
                color = Color.Black.copy(alpha = 0.7f),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 18.dp)
            )

            Button(
                onClick = { viewModel.navigateToLogin() },
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .padding(top = 18.dp)
            ) {
                Text(
                    text = "Continuar",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(12.dp)
                )
            }
        }
    }
}