package cardosofgui.android.pokedexcompose.core.usecase

import cardosofgui.android.pokedexcompose.core.repository.PokemonRepository

class FavoritePokemonUseCase(
    private val pokemonRepository: PokemonRepository
) {
    fun removeFavoritePokemon(pokemonId: Long?): Boolean {
        if(pokemonId == null) return false
        return pokemonRepository.removeFavoritePokemon(pokemonId)
    }

    fun addFavoritePokemon(pokemonId: Long?): Boolean {
        if(pokemonId == null) return false
        return pokemonRepository.addFavoritePokemon(pokemonId)
    }
}