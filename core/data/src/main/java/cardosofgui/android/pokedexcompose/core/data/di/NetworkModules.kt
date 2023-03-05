package cardosofgui.android.pokedexcompose.core.data.di

import cardosofgui.android.pokedexcompose.core.data.network.service.PokemonApiClientImpl
import cardosofgui.android.pokedexcompose.core.network.service.PokemonApiClient
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModules = module {
    single<PokemonApiClient>{
        PokemonApiClientImpl(createService())
    }
}

fun createService(): HttpClient {
    return HttpClient {
        install(ContentNegotiation) {
            val converter = KotlinxSerializationConverter(Json {
                prettyPrint = true
                ignoreUnknownKeys = true
            })

            register(ContentType.Application.Json, converter)
        }

        install(Logging) {
            level = LogLevel.ALL
        }
    }
}