package cardosofgui.android.pokedexcompose.core.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import cardosofgui.android.pokedexcompose.core.network.model.PokemonTypes
import cardosofgui.android.pokedexcompose.core.network.model.Type
import cardosofgui.android.pokedexcompose.core.network.model.Types

@Entity(tableName = "type")
data class TypeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val name: String? = null
) {
    fun toDomain(): Types {
        return Types(
            type = Type(
                name = PokemonTypes.valueOf(name ?: "")),
            )
    }

    fun domainToEntity(type: Types?): TypeEntity {
        return TypeEntity(
            id = type?.type?.id,
            name = type?.type?.name?.name
        )
    }
}