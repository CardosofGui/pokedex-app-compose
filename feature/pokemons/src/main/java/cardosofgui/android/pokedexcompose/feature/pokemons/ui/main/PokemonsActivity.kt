package cardosofgui.android.pokedexcompose.feature.pokemons.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import cardosofgui.android.core.components.ui.theme.PokemonTheme
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
        }
    }

    companion object {
        fun getStartIntent(
            context: Context
        ): Intent {
            return Intent(context, PokemonsActivity::class.java)
        }
    }
}