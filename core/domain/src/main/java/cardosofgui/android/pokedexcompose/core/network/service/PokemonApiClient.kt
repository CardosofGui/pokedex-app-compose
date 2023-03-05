package cardosofgui.android.pokedexcompose.core.network.service

import cardosofgui.android.pokedexcompose.core.network.model.Pokemon

interface PokemonApiClient {
    suspend fun getPokemon(
        pokemonId: Int? = null,
        pokemonName: String? = null,
    ): Pokemon
}