package cardosofgui.android.pokedexcompose.feature.details.ui.state

import cardosofgui.android.core.components.utils.UIState
import cardosofgui.android.pokedexcompose.core.network.model.Pokemon

data class DetailsState(
    val isLoading: Boolean = false,
    val pokemonDetails: Pokemon? = null
): UIState