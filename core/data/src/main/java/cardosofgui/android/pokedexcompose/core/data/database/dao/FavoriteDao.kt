package cardosofgui.android.pokedexcompose.core.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import cardosofgui.android.pokedexcompose.core.data.database.entity.FavoriteEntity

@Dao
interface FavoriteDao {
    @Insert
    suspend fun insertFavorite(favoriteEntity: FavoriteEntity): Long
    @Query("DELETE FROM favorite WHERE pokemonId = :pokemonId")
    suspend fun deleteFavorite(pokemonId: Long)
    @Query("SELECT * FROM favorite WHERE pokemonId = :pokemonId")
    suspend fun queryFavorite(pokemonId: Long): FavoriteEntity?

}