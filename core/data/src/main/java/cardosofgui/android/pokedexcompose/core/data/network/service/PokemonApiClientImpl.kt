package cardosofgui.android.pokedexcompose.core.data.network.service

import cardosofgui.android.pokedexcompose.core.data.network.model.PokemonNetwork
import cardosofgui.android.pokedexcompose.core.network.model.Pokemon
import cardosofgui.android.pokedexcompose.core.network.service.BasePath
import cardosofgui.android.pokedexcompose.core.network.service.PokemonApiClient
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class PokemonApiClientImpl(
    private val ktorClient: HttpClient,
): PokemonApiClient {
    override suspend fun getPokemon(
        pokemonId: Int?,
        pokemonName: String?,
    ): Pokemon {
        val response: PokemonNetwork = ktorClient.get("${BasePath.baseUrl}1").body()
        return response.provideToModel()
    }
}