package cardosofgui.android.pokedexcompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cardosofgui.android.core.navigation.AppDeeplinkNavigation
import cardosofgui.android.core.navigation.routes.PokemonsRoutes
import org.koin.android.ext.android.inject

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val appDeeplinkNavigation by inject<AppDeeplinkNavigation>()

            appDeeplinkNavigation.navigate(this, PokemonsRoutes())

            // TODO implementar splashScreen
        }
    }
}