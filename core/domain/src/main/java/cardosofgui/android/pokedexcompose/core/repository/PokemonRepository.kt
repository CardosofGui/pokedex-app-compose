package cardosofgui.android.pokedexcompose.core.repository

import cardosofgui.android.pokedexcompose.core.network.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemonById(id: Long): Flow<Pokemon?>
    suspend fun getPokemonList(): Flow<List<Pokemon>>
    suspend fun getPokemonListFromNetwork(limit: Long, offset: Long)
    fun getFavoritePokemonList(): Flow<List<Pokemon>>
    suspend fun addFavoritePokemon(pokemon: Pokemon?)
    suspend fun removeFavoritePokemon(pokemon: Pokemon?)
}