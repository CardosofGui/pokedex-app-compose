package cardosofgui.android.pokedexcompose.core.usecase

import cardosofgui.android.pokedexcompose.core.network.model.Pokemon
import cardosofgui.android.pokedexcompose.core.network.service.PokemonApiClient
import cardosofgui.android.pokedexcompose.core.repository.PokemonRepository

class GetPokemonUseCase(
    private val pokemonRepository: PokemonRepository,
) {
    suspend fun getPokemonById(id: Long): Pokemon? {
        return try {
            pokemonRepository.getPokemonById(id = id)
        } catch(e : Exception) {
            null
        }
    }

    suspend fun getPokemonList(
        limit: Long,
        offset: Long
    ): List<Pokemon>? {
        return pokemonRepository.getPokemonList(offset = offset, limit = limit)
    }

}