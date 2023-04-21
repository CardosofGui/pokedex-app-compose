package cardosofgui.android.pokedexcompose.core.data.di

import cardosofgui.android.pokedexcompose.core.data.repository.PokemonRepositoryImpl
import cardosofgui.android.pokedexcompose.core.repository.PokemonRepository
import org.koin.dsl.module

val repositoryModules = module {
    single {
        PokemonRepositoryImpl(
            pokemonApiClient = get()
        ) as PokemonRepository
    }
}