package cardosofgui.android.pokedexcompose.core.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "favorite"
)
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val pokemonId: Long? = null
)