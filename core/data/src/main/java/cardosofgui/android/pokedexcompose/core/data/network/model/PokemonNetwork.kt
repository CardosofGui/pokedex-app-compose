package cardosofgui.android.pokedexcompose.core.data.network.model

import cardosofgui.android.pokedexcompose.core.network.model.Pokemon
import kotlinx.serialization.Serializable
@Serializable
data class PokemonNetwork(
    val id: Long? = null,
    val name: String? = null,
    val url: String? = null,
    val height: Long? = null,
    val weight: Long? = null,
    val types: List<TypesNetwork>? = null,
    val stats: List<StatsNetwork>? = null
) {
    fun provideToModel(): Pokemon {
        val urlSplit = url?.split("/") ?: emptyList()
        val idByUrlSplit = urlSplit.getOrNull(urlSplit.size-2)?.toLong()

        return Pokemon(
            id = this.id ?: idByUrlSplit,
            name = this.name,
            mainImage = getMainImageUrl(this.id ?: idByUrlSplit ?: 0),
            secondaryImage = getSecondaryImageUrl(this.id ?: idByUrlSplit ?: 0),
            height = this.height,
            weight = this.weight,
            types = types?.map { it.provideToModel() },
            stats = stats?.map { it.provideToModel() }
        )
    }
}
fun getMainImageUrl(id: Long) = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
fun getSecondaryImageUrl(id: Long) = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"