package cardosofgui.android.pokedexcompose.feature.details.ui.state

import cardosofgui.android.core.components.utils.UIAction

sealed class DetailsAction: UIAction {
    class ShowToast(val message: String): DetailsAction()

}