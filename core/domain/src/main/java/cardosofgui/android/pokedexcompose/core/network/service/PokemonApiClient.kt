package cardosofgui.android.pokedexcompose.core.network.service

import cardosofgui.android.pokedexcompose.core.network.model.Pokemon

interface PokemonApiClient {
    suspend fun getPokemon(
        pokemonId: Long? = null,
        pokemonName: String? = null,
    ): Pokemon
    suspend fun getPokemonList(
        limit: Long?,
        offset: Long?
    ): List<Pokemon>?
}