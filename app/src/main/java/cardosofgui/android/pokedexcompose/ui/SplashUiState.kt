package cardosofgui.android.pokedexcompose.ui

import cardosofgui.android.core.components.utils.UIState
import cardosofgui.android.pokedexcompose.core.network.model.UserSettings

data class SplashUiState(
    val isLoading: Boolean = false,
    val user: UserSettings? = null,
    val newUsername: String = "",
    val hasUser: Boolean = false,
    val navigation: SplashNavigation = SplashNavigation.SPLASH
): UIState

enum class SplashNavigation {
    SPLASH, ONBOARDING, LOGIN, APP
}
