package cardosofgui.android.pokedexcompose.core.data.network.model

import cardosofgui.android.pokedexcompose.core.network.model.Pokemon
import kotlinx.serialization.Serializable

@Serializable
data class PokemonNetwork(
    val id: Long? = null,
    val name: String? = null,
    val sprites: SpriteNetwork? = null
) {
    fun provideToModel(): Pokemon {
        return Pokemon(
            id = this.id,
            name = this.name,
            sprites = this.sprites?.provideToModel()
        )
    }
}