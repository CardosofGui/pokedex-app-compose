package cardosofgui.android.core.components.utils

import androidx.compose.ui.graphics.Color
import cardosofgui.android.pokedexcompose.core.network.model.PokemonTypes

class Extensions {

    companion object {
        fun PokemonTypes?.getBackgroundColor(): Color {
            return when(this) {
                PokemonTypes.NORMAL -> Color(0xFFAAA67F)
                PokemonTypes.FIRE -> Color(0xFFF57D31)
                PokemonTypes.WATER -> Color(0xFF6493EB)
                PokemonTypes.ELECTRIC -> Color(0xFFF9CF30)
                PokemonTypes.BUG -> Color(0xFFA7B723)
                PokemonTypes.ICE -> Color(0xFF9AD6DF)
                PokemonTypes.FIGHTING -> Color(0xFFC12239)
                PokemonTypes.POISON -> Color(0xFFA43E9E)
                PokemonTypes.GROUND -> Color(0xFFDEC16B)
                PokemonTypes.FLYING -> Color(0xFFA891EC)
                PokemonTypes.PSYCHIC -> Color(0xFFFB5584)
                PokemonTypes.GRASS -> Color(0xFF74CB48)
                PokemonTypes.ROCK -> Color(0xFFB69E31)
                PokemonTypes.GHOST -> Color(0xFF70559B)
                PokemonTypes.DRAGON -> Color(0xFF7037FF)
                PokemonTypes.DARK -> Color(0xFF75574C)
                PokemonTypes.STEEL -> Color(0xFFB7B9D0)
                PokemonTypes.FAIRY -> Color(0xFFE69EAC)
                else -> Color(0xFFCCCCCC)
            }
        }
    }

}