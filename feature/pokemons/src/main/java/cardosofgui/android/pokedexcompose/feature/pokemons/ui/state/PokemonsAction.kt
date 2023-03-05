package cardosofgui.android.pokedexcompose.feature.pokemons.ui.state

sealed class PokemonsAction {
    data class OpenPokemonDetails(val pokemonId: Long): PokemonsAction()
}
