package cardosofgui.android.pokedexcompose.core.data.network.model

import cardosofgui.android.pokedexcompose.core.network.model.Moves
import kotlinx.serialization.Serializable

@Serializable
data class MovesNetwork(
    val name: String
) {
    fun provideToModel(): Moves {
        return Moves(
            name = this.name
        )
    }
}