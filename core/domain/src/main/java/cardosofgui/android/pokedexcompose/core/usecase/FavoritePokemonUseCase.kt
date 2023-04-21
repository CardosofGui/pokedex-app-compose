package cardosofgui.android.pokedexcompose.core.usecase

import cardosofgui.android.pokedexcompose.core.network.model.Pokemon
import cardosofgui.android.pokedexcompose.core.repository.PokemonRepository

class FavoritePokemonUseCase(
    private val pokemonRepository: PokemonRepository
) {
    fun removeFavoritePokemon(pokemon: Pokemon?): Boolean {
        if(pokemon == null) return false
        return pokemonRepository.removeFavoritePokemon(pokemon)
    }

    fun addFavoritePokemon(pokemon: Pokemon?): Boolean {
        if(pokemon == null) return false
        return pokemonRepository.addFavoritePokemon(pokemon)
    }
}