package cardosofgui.android.pokedexcompose.core.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cardosofgui.android.pokedexcompose.core.data.database.entity.StatsEntity


@Dao
interface StatsDao {

    @Query("SELECT * FROM stats WHERE pokemonId = :pokemonId")
    fun queryAllStatsByPokemonId(pokemonId: Long): List<StatsEntity>

    @Query("DELETE from stats")
    suspend fun deleteAllStats()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(statsEntity: StatsEntity): Long

}