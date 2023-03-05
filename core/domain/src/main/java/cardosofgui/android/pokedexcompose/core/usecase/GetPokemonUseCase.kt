package cardosofgui.android.pokedexcompose.core.usecase

import cardosofgui.android.pokedexcompose.core.network.model.Pokemon
import cardosofgui.android.pokedexcompose.core.network.service.PokemonApiClient
class GetPokemonUseCase(
    private val pokemonApiClient: PokemonApiClient,
) {
    suspend fun getPokemonById(id: Long): Pokemon? {
        return try {
            pokemonApiClient.getPokemon(pokemonId = id)
        } catch(e : Exception) {
            null
        }
    }

    suspend fun getPokemonList(
        limit: Long,
        offset: Long
    ): List<Pokemon>? {
        return pokemonApiClient.getPokemonList(offset = offset, limit = limit)
    }

}