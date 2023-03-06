package cardosofgui.android.pokedexcompose.feature.details.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cardosofgui.android.core.components.R
import cardosofgui.android.core.components.ui.theme.PokemonTheme
import cardosofgui.android.core.navigation.routes.DetailsRoutes.Companion.POKEMON_ID_PARAM
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

class DetailsMainActivity : ComponentActivity() {
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

        overridePendingTransition(R.anim.slide_out_top, R.anim.slide_in_top)
    }

    companion object {
        fun getStartIntent(
            context: Context
        ): Intent {
            return Intent(context, DetailsMainActivity::class.java)
        }
    }
}