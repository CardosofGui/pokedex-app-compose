package cardosofgui.android.pokedexcompose.core.data.network.model

import cardosofgui.android.pokedexcompose.core.network.model.Sprite
import kotlinx.serialization.Serializable


@Serializable
data class SpriteNetwork(
    val front_default: String? = null,
) {
    fun provideToModel(): Sprite {
        return  Sprite(
            front_default = this.front_default
        )
    }
}
