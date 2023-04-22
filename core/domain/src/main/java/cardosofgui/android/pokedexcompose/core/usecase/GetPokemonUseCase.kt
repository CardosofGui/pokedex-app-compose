package cardosofgui.android.pokedexcompose.core.usecase

import cardosofgui.android.pokedexcompose.core.network.model.Pokemon
import cardosofgui.android.pokedexcompose.core.network.service.PokemonApiClient
import cardosofgui.android.pokedexcompose.core.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class GetPokemonUseCase(
    private val pokemonRepository: PokemonRepository,
) {
    suspend fun getPokemonById(id: Long): Flow<Pokemon?> {
        return pokemonRepository.getPokemonById(id = id)
    }

    suspend fun getPokemonList(
        limit: Long,
        offset: Long
    ): Flow<List<Pokemon>> {
        return pokemonRepository.getPokemonList(offset = offset, limit = limit)
    }

    fun getFavoritePokemonList(): Flow<List<Pokemon>?> {
        return pokemonRepository.getFavoritePokemonList()
    }
}