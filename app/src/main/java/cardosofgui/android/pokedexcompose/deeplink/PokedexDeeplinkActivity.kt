package cardosofgui.android.pokedexcompose.deeplink

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cardosofgui.android.pokedexcompose.feature.details.navigation.DetailsDeeplinkModule
import cardosofgui.android.pokedexcompose.feature.pokemons.navigation.PokemonsDeeplinkModule
import com.airbnb.deeplinkdispatch.DeepLinkHandler
import org.koin.android.ext.android.inject

@DeepLinkHandler(
    PokemonsDeeplinkModule::class,
    DetailsDeeplinkModule::class
)
class PokedexDeeplinkActivity : ComponentActivity() {
    private val deepLinkDelegate: DeepLinkDelegate by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {  }

        handleIntent(intent)
    }


    private fun handleIntent(intent: Intent){
        val uri = intent.data ?: return
        val newIntent = intent.apply { data = uri }
//        deepLinkDelegate.dispatchFrom(this, newIntent)
        finish()
    }
}