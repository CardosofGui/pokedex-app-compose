package cardosofgui.android.pokedexcompose.core.data.repository

internal object UserData {

    private var favoritePokemonList: MutableList<Long> = mutableListOf()

    fun addFavoritePokemon(pokemonId: Long?): Boolean {
        if(pokemonId == null) return false
        favoritePokemonList.add(pokemonId)

        return isFavoritePokemon(pokemonId)
    }

    fun removeFavoritePokemon(pokemonId: Long?): Boolean {
        if(pokemonId == null) return false
        favoritePokemonList.remove(pokemonId)

        return isFavoritePokemon(pokemonId)
    }

    fun isFavoritePokemon(pokemonId: Long?): Boolean {
        return favoritePokemonList.contains(pokemonId)
    }

    fun getFavoritePokemonList(): List<Long> {
        return favoritePokemonList
    }

}