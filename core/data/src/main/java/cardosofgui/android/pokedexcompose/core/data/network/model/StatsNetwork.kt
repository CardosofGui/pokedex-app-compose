package cardosofgui.android.pokedexcompose.core.data.network.model

import cardosofgui.android.pokedexcompose.core.network.model.Stat
import cardosofgui.android.pokedexcompose.core.network.model.Stats
import kotlinx.serialization.Serializable

@Serializable
data class StatsNetwork(
    val base_stat: Long? = null,
    val stat: StatNetwork? = null
) {
    fun provideToModel() = Stats(
        baseStat = this.base_stat,
        stat = stat?.provideToModel()
    )
}

@Serializable
data class StatNetwork(
    val name: String? = null
) {
    fun provideToModel() = Stat(
        name = this.name
    )
}
