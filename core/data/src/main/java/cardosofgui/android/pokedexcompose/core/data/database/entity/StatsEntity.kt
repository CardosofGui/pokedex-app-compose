package cardosofgui.android.pokedexcompose.core.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import cardosofgui.android.pokedexcompose.core.network.model.Stat
import cardosofgui.android.pokedexcompose.core.network.model.Stats

@Entity(
    tableName = "stats",
    foreignKeys = [
        ForeignKey(
            entity = PokemonEntity::class,
            parentColumns = ["id"],
            childColumns = ["pokemonId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class StatsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val baseStat: Long? = null,
    val name: String? = null,
    val pokemonId: Long? = null
) {
    fun toDomain() = Stats(
        id = id,
        baseStat = baseStat,
        stat = Stat(
            name = name
        )
    )

    fun domainToEntity(stats: Stats, pokemonId: Long) = StatsEntity(
        id = stats.id,
        baseStat = stats.baseStat,
        name = stats.stat?.name ?: "",
        pokemonId = pokemonId
    )
}