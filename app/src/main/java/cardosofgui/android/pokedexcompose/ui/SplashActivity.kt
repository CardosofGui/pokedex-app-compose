package cardosofgui.android.pokedexcompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cardosofgui.android.core.components.ui.theme.PokemonTheme
import cardosofgui.android.core.navigation.AppDeeplinkNavigation
import cardosofgui.android.core.navigation.routes.PokemonsRoutes
import cardosofgui.android.pokedexcompose.ui.login.LoginScreen
import cardosofgui.android.pokedexcompose.ui.onboarding.OnBoardingScreen
import cardosofgui.android.pokedexcompose.ui.splash.SplashScreen
import org.koin.android.ext.android.inject

class SplashActivity : ComponentActivity() {

    internal val viewModel by inject<SplashViewModel>()

    @OptIn(ExperimentalLifecycleComposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonTheme {
                val state by viewModel.state.collectAsStateWithLifecycle()
                val navigation = state.navigation

                Crossfade(targetState = navigation) { nav ->
                    when(nav) {
                        SplashNavigation.SPLASH -> {
                            SplashScreen()
                        }
                        SplashNavigation.ONBOARDING -> {
                            OnBoardingScreen()
                        }
                        SplashNavigation.LOGIN -> {
                            LoginScreen()
                        }
                        SplashNavigation.APP -> {
                            val appDeeplinkNavigation by inject<AppDeeplinkNavigation>()

                            appDeeplinkNavigation.navigate(this, PokemonsRoutes())

                            finish()
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}