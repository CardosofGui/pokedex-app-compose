package cardosofgui.android.pokedexcompose.core.data.repository

import cardosofgui.android.pokedexcompose.core.network.model.Pokemon
import cardosofgui.android.pokedexcompose.core.network.service.PokemonApiClient
import cardosofgui.android.pokedexcompose.core.repository.PokemonRepository
import okhttp3.internal.wait

class PokemonRepositoryImpl(
    private val pokemonApiClient: PokemonApiClient
): PokemonRepository {
    override suspend fun getPokemonById(id: Long): Pokemon {
        var pokemon = pokemonApiClient.getPokemon(pokemonId = id)

        pokemon = pokemon.copy(
            favoriteStatus = UserData.isFavoritePokemon(pokemon)
        )

        return pokemon
    }
    override suspend fun getPokemonList(limit: Long, offset: Long): List<Pokemon>? {
        return pokemonApiClient.getPokemonList(limit = limit, offset = offset)
    }

    override fun getFavoritePokemonList(): List<Pokemon> {
        return UserData.getFavoritePokemonList()
    }

    override fun addFavoritePokemon(pokemon: Pokemon?): Boolean {
        return UserData.addFavoritePokemon(pokemon)
    }
    override fun removeFavoritePokemon(pokemon: Pokemon?): Boolean {
        return UserData.removeFavoritePokemon(pokemon)
    }
}