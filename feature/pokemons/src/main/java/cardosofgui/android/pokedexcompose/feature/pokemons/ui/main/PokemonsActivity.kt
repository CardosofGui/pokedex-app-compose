package cardosofgui.android.pokedexcompose.feature.pokemons.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import cardosofgui.android.core.components.R
import cardosofgui.android.core.components.ui.theme.PokemonTheme
import cardosofgui.android.core.navigation.AppDeeplinkNavigation
import cardosofgui.android.core.navigation.routes.DetailsRoutes
import cardosofgui.android.pokedexcompose.feature.pokemons.ui.state.PokemonsAction
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonsActivity : ComponentActivity() {

    internal val viewModel: PokemonsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonTheme {
                PokemonsScreen(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxSize()
                )
            }

            LaunchedEffect(Unit) {
                viewModel.action.collect { action ->
                    when(action) {
                        is PokemonsAction.OpenPokemonDetails -> {
                            val appDeeplinkNavigation by inject<AppDeeplinkNavigation>()

                            appDeeplinkNavigation.navigate(
                                this@PokemonsActivity,
                                DetailsRoutes(),
                                Bundle().apply {
                                    putLong(DetailsRoutes.POKEMON_ID_PARAM, action.pokemonId)
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchFavoritePokemonList()
    }

    companion object {
        fun getStartIntent(
            context: Context
        ): Intent {
            return Intent(context, PokemonsActivity::class.java)
        }
    }
}