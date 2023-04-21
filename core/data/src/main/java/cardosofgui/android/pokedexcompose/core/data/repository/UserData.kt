package cardosofgui.android.pokedexcompose.core.data.repository

import cardosofgui.android.pokedexcompose.core.network.model.Pokemon

internal object UserData {

    private var favoritePokemonList: MutableList<Pokemon> = mutableListOf()

    fun addFavoritePokemon(pokemon: Pokemon?): Boolean {
        if(pokemon == null) return false
        favoritePokemonList.add(pokemon)

        return isFavoritePokemon(pokemon)
    }

    fun removeFavoritePokemon(pokemon: Pokemon?): Boolean {
        if(pokemon == null) return false
        favoritePokemonList.remove(pokemon)

        return isFavoritePokemon(pokemon)
    }

    fun isFavoritePokemon(pokemon: Pokemon?): Boolean {
        return favoritePokemonList.any { it.id == pokemon?.id }
    }

    fun getFavoritePokemonList(): List<Pokemon> {
        return favoritePokemonList
    }

}