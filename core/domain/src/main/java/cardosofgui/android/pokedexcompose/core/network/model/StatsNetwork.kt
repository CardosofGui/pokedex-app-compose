package cardosofgui.android.pokedexcompose.core.network.model

data class Stats(
    val id: Long? = null,
    val baseStat: Long? = null,
    val stat: Stat? = null
)

data class Stat(
    val name: String? = null
)
