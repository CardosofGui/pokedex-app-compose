package cardosofgui.android.pokedexcompose.core.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cardosofgui.android.pokedexcompose.core.data.database.entity.TypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TypeDao {

    @Query("SELECT * FROM type")
    fun queryAll(): Flow<List<TypeEntity>>

    @Query("SELECT * FROM type WHERE id = :id")
    fun queryById(id: Long): Flow<TypeEntity?>

    @Query("SELECT * FROM type WHERE name = :name")
    fun queryByName(name: String): Flow<TypeEntity?>

    @Query("DELETE from type")
    suspend fun deleteAllType()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(typeEntity: TypeEntity): Long

}