package cardosofgui.android.pokedexcompose.core.repository

import cardosofgui.android.pokedexcompose.core.network.model.Pokemon

interface PokemonRepository {
    suspend fun getPokemonById(id: Long): Pokemon?
    suspend fun getPokemonList(limit: Long, offset: Long): List<Pokemon>?
    fun addFavoritePokemon(pokemonId: Long?): Boolean
    fun removeFavoritePokemon(pokemonId: Long?): Boolean
}