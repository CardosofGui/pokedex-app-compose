package cardosofgui.android.pokedexcompose.core.repository

import cardosofgui.android.pokedexcompose.core.network.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemonById(id: Long): Flow<Pokemon?>
    suspend fun getPokemonList(limit: Long, offset: Long): Flow<List<Pokemon>>
    fun getFavoritePokemonList(): Flow<List<Pokemon>?>
    suspend fun addFavoritePokemon(pokemon: Pokemon?)
    suspend fun removeFavoritePokemon(pokemon: Pokemon?)
}