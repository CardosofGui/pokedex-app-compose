package cardosofgui.android.pokedexcompose.core.data.network.model

import cardosofgui.android.pokedexcompose.core.network.model.PokemonTypes
import cardosofgui.android.pokedexcompose.core.network.model.Type
import cardosofgui.android.pokedexcompose.core.network.model.Types
import kotlinx.serialization.Serializable

@Serializable
data class TypesNetwork(
    val type: TypeNetwork
) {
    fun provideToModel() = Types(
        this.type.provideToModel()
    )
}

@Serializable
data class TypeNetwork(
    val name: String
) {
    fun provideToModel() = Type(
        name = PokemonTypes.valueOf(this.name.uppercase())
    )
}