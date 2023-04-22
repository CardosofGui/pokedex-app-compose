package cardosofgui.android.pokedexcompose.core.data.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import cardosofgui.android.pokedexcompose.core.network.model.Pokemon
import cardosofgui.android.pokedexcompose.core.network.model.Types

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long? = null,
    val name: String? = null,
    val mainImage: String? = null,
    val secondaryImage: String? = null,
    val height: Long? = null,
    val weight: Long? = null,
    @Embedded(prefix = "type1_")
    val type1: TypeEntity? = null,
    @Embedded(prefix = "type2_")
    val type2: TypeEntity? = null
) {
    fun toDomain(): Pokemon {
        return Pokemon(
            id = id,
            name = name,
            mainImage = mainImage,
            secondaryImage = secondaryImage,
            height = height,
            weight = weight,
            types = listOfNotNull(type1?.toDomain(), type2?.toDomain())
        )
    }

    fun domainToEntity(pokemon: Pokemon): PokemonEntity {
        return PokemonEntity(
            id = pokemon.id,
            name = pokemon.name,
            mainImage = pokemon.mainImage,
            secondaryImage = pokemon.secondaryImage,
            height = pokemon.height,
            weight = pokemon.weight,
            type1 = TypeEntity().domainToEntity(pokemon.types?.getOrNull(0)),
            type2 = TypeEntity().domainToEntity(pokemon.types?.getOrNull(1))
        )
    }
}

data class FullPokemonData(
    @Embedded
    val pokemon: PokemonEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "pokemonId"
    )
    val stats: List<StatsEntity>
) {
    fun toDomain(): Pokemon {
        return pokemon.toDomain().copy(
            stats = stats.map { it.toDomain() }
        )
    }
}
