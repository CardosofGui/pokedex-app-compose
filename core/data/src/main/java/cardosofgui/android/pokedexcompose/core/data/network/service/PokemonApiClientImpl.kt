package cardosofgui.android.pokedexcompose.core.data.network.service

import cardosofgui.android.pokedexcompose.core.data.network.model.PokemonNetwork
import cardosofgui.android.pokedexcompose.core.data.network.utils.ResultGeneric
import cardosofgui.android.pokedexcompose.core.network.model.Pokemon
import cardosofgui.android.pokedexcompose.core.network.service.BasePath
import cardosofgui.android.pokedexcompose.core.network.service.PokemonApiClient
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class PokemonApiClientImpl(
    private val ktorClient: HttpClient,
): PokemonApiClient {
    override suspend fun getPokemon(
        pokemonId: Long?,
        pokemonName: String?,
    ): Pokemon {
        val response: PokemonNetwork = ktorClient.get("${BasePath.baseUrl}$pokemonId").body()
        return response.provideToModel()
    }

    override suspend fun getPokemonList(
        limit: Long?,
        offset: Long?
    ): List<Pokemon>? {
       val response: ResultGeneric<PokemonNetwork> = ktorClient.get("${BasePath.baseUrl}") {
           parameter("offset", offset)
           parameter("limit", limit)
       }.body()

        return response.results?.map { it.provideToModel() }
    }
}