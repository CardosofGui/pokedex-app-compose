package cardosofgui.android.pokedexcompose.feature.details.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cardosofgui.android.core.components.ui.theme.PokemonTheme
import cardosofgui.android.core.navigation.routes.DetailsRoutes.Companion.POKEMON_ID_PARAM
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

class DetailsMainActivity : ComponentActivity() {
    @OptIn(ExperimentalLifecycleComposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pokemonId = if(intent.extras != null) {
            if (intent.hasExtra(POKEMON_ID_PARAM)) {
                intent.getLongExtra(POKEMON_ID_PARAM, 0)
            } else null
        } else null

        setContent {
            val detailsViewModel = getViewModel<DetailsViewModel>() {
                parametersOf(pokemonId)
            }

            PokemonTheme {
                DetailsScreen(
                    viewModel = detailsViewModel
                )
            }
        }
    }

    companion object {
        fun getStartIntent(
            context: Context
        ): Intent {
            return Intent(context, DetailsMainActivity::class.java)
        }
    }
}