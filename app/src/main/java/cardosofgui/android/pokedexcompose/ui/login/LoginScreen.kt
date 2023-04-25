package cardosofgui.android.pokedexcompose.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cardosofgui.android.pokedexcompose.R
import cardosofgui.android.pokedexcompose.ui.SplashActivity

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)
@Composable
internal fun SplashActivity.LoginScreen(
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val name = state.newUsername

    Box(modifier = modifier
        .fillMaxSize()
        .background(Color.White)) {

        IconButton(
            onClick = { viewModel.navigateToOnboarding() },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(12.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back_icon),
                contentDescription = "",
                tint = Color.Black,
                modifier = Modifier
                    .size(48.dp)
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(18.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.login),
                contentDescription = "",
                modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                text = "Está pronto para essa aventura?",
                fontSize = 26.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                text = "Antes de começar, por favor nós diga como devemos te chamar!",
                fontSize = 14.sp,
                color = Color.Black.copy(alpha = 0.7f),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 18.dp)
            )

            TextField(
                value = name,
                onValueChange = { viewModel.updateUsername(it) },
                maxLines = 1,
                shape = RoundedCornerShape(50.dp),
                placeholder = {
                    Text(
                        text = "Digite seu nome",
                        color = Color.Black.copy(alpha = 0.7f)
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black.copy(alpha = 0.7f),
                    textColor = Color.Black.copy(alpha = 0.7f),
                    containerColor = Color.Gray.copy(alpha = 0.1f)
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .padding(top = 18.dp)
            )

            Button(
                onClick = {
                    viewModel.updateUser()
                },
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
                    text = "Começar!",
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