package cardosofgui.android.core.components.utils

import androidx.compose.ui.graphics.Color
import cardosofgui.android.pokedexcompose.core.network.model.PokemonTypes

class Extensions {

    companion object {
        fun PokemonTypes?.getBackgroundColor(): Color {
            return when(this) {
                PokemonTypes.NORMAL -> Color(0xFFA8A878)
                PokemonTypes.FIRE -> Color(0xFFF08030)
                PokemonTypes.WATER -> Color(0xFF6890F0)
                PokemonTypes.ELECTRIC -> Color(0xFFF8D030)
                PokemonTypes.BUG -> Color(0xFF78C850)
                PokemonTypes.ICE -> Color(0xFF98D8D8)
                PokemonTypes.FIGHTING -> Color(0xFFC03028)
                PokemonTypes.POISON -> Color(0xFFA040A0)
                PokemonTypes.GROUND -> Color(0xFFE0C068)
                PokemonTypes.FLYING -> Color(0xFFA890F0)
                PokemonTypes.PSYCHIC -> Color(0xFFF85888)
                PokemonTypes.GRASS -> Color(0xFF78C850)
                PokemonTypes.BUG -> Color(0xFFA8B820)
                PokemonTypes.ROCK -> Color(0xFFB8A038)
                PokemonTypes.GHOST -> Color(0xFF705898)
                PokemonTypes.DRAGON -> Color(0xFF7038F8)
                PokemonTypes.DARK -> Color(0xFF705848)
                PokemonTypes.STEEL -> Color(0xFFB8B8D0)
                PokemonTypes.FAIRY -> Color(0xFFEE99AC)
                else -> Color(0xFFCCCCCC)
            }
        }
    }

}