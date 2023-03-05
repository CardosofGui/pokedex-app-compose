package cardosofgui.android.pokedexcompose.core.usecase

import cardosofgui.android.pokedexcompose.core.network.model.Pokemon
import cardosofgui.android.pokedexcompose.core.network.service.PokemonApiClient

class GetPokemonUseCase(
    private val pokemonApiClient: PokemonApiClient,
) {
    suspend fun getPokemonByName(name: String): Pokemon? {
        return try {
            pokemonApiClient.getPokemon()
        } catch(e : Exception) {
            null
        }
    }

}