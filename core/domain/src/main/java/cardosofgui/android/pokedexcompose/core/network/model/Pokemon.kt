package cardosofgui.android.pokedexcompose.core.network.model

data class Pokemon(
    val id: Long? = null,
    val name: String? = null,
    val mainImage: String? = null,
    val secondaryImage: String? = null,
    val height: Long? = null,
    val weight: Long? = null,
    val types: List<Types>? = null,
    val stats: List<Stats>? = null
)