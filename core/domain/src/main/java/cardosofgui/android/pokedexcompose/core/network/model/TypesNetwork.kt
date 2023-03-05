package cardosofgui.android.pokedexcompose.core.network.model

data class Types(
    val type: Type
)

data class Type(
    val name: PokemonTypes?
)
enum class PokemonTypes {
    BUG, DARK, DRAGON, ELECTRIC, FAIRY, FIGHTING, FIRE, FLYING, GHOST, GRASS, GROUND, ICE, NORMAL, POISON, PSYCHIC, ROCK, STEEL, WATER
}