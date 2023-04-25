package cardosofgui.android.pokedexcompose.core.usecase

import cardosofgui.android.pokedexcompose.core.network.model.Pokemon
import cardosofgui.android.pokedexcompose.core.repository.PokemonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GetPokemonUseCase(
    private val pokemonRepository: PokemonRepository,
    private val dispatcher: CoroutineScope = CoroutineScope(Dispatchers.IO)
) {
    suspend fun getPokemonById(id: Long): Flow<Pokemon?> {
        return pokemonRepository.getPokemonById(id = id)
    }

    suspend fun getPokemonList(
        limit: Long,
        offset: Long
    ): Flow<List<Pokemon>> {
        val pokemonList = MutableStateFlow<List<Pokemon>>(emptyList())

        dispatcher.launch {
            pokemonRepository.getPokemonList().collect { pokemonListDb ->
                pokemonList.value = pokemonListDb

                if (pokemonListDb.isEmpty()) {
                    refreshPokemonList(limit = limit, offset = offset)
                }
            }
        }

        return pokemonList
    }

    fun getFavoritePokemonList(): Flow<List<Pokemon>> {
        val pokemonList = MutableStateFlow<List<Pokemon>>(emptyList())

        dispatcher.launch {
            pokemonRepository.getFavoritePokemonList().collect { pokemonListDb ->
                pokemonList.value = pokemonListDb
            }
        }

        return pokemonList
    }

    suspend fun refreshPokemonList(limit: Long, offset: Long) {
        pokemonRepository.getPokemonListFromNetwork(limit = limit, offset = offset)
    }
}