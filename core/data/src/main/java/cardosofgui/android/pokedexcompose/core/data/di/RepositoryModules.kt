package cardosofgui.android.pokedexcompose.core.data.di

import cardosofgui.android.pokedexcompose.core.data.repository.PokemonRepositoryImpl
import cardosofgui.android.pokedexcompose.core.data.repository.UserRepositoryImpl
import cardosofgui.android.pokedexcompose.core.repository.PokemonRepository
import cardosofgui.android.pokedexcompose.core.repository.UserRepository
import org.koin.dsl.module

val repositoryModules = module {
    single {
        PokemonRepositoryImpl(
            pokemonApiClient = get(),
            pokemonDao = get(),
            statsDao = get(),
            favoriteDao = get()
        ) as PokemonRepository
    }

    single {
        UserRepositoryImpl(
            context = get()
        ) as UserRepository
    }
}