package cardosofgui.android.pokedexcompose.core.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import cardosofgui.android.pokedexcompose.core.data.database.entity.FullPokemonData
import cardosofgui.android.pokedexcompose.core.data.database.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemon")
    fun queryAll(): Flow<List<FullPokemonData>>
    @Query("SELECT * FROM pokemon WHERE id = :id")
    fun queryById(id: Long): Flow<FullPokemonData?>
    @Query("SELECT * FROM pokemon WHERE name = :name")
    fun queryByName(name: String): Flow<FullPokemonData?>
    @Query("SELECT pokemon.* FROM pokemon, favorite WHERE pokemon.id = favorite.pokemonId  ORDER BY favorite.pokemonId ASC")
    fun queryAllFavorites(): Flow<List<FullPokemonData>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemonEntity: PokemonEntity): Long
    @Query("DELETE from pokemon")
    suspend fun deleteAllPokemon()
}