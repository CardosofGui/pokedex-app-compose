package cardosofgui.android.pokedexcompose.feature.pokemons.ui.state

import cardosofgui.android.core.components.utils.UIAction

sealed class PokemonsAction: UIAction {
    data class OpenPokemonDetails(val pokemonId: Long): PokemonsAction()
}
