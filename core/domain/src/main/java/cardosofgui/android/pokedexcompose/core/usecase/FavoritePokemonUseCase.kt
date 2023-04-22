package cardosofgui.android.pokedexcompose.core.usecase

import cardosofgui.android.pokedexcompose.core.network.model.Pokemon
import cardosofgui.android.pokedexcompose.core.repository.PokemonRepository

class FavoritePokemonUseCase(
    private val pokemonRepository: PokemonRepository
) {
    suspend fun removeFavoritePokemon(pokemon: Pokemon?) {
        pokemonRepository.removeFavoritePokemon(pokemon)
    }

    suspend fun addFavoritePokemon(pokemon: Pokemon?) {
        pokemonRepository.addFavoritePokemon(pokemon)
    }
}