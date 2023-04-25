package cardosofgui.android.pokedexcompose.ui

import androidx.lifecycle.viewModelScope
import cardosofgui.android.core.components.utils.state.ViewModel
import cardosofgui.android.pokedexcompose.core.network.model.UserSettings
import cardosofgui.android.pokedexcompose.core.usecase.GetUserUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(
    private val getUserUseCase: GetUserUseCase,
): ViewModel<SplashUiState>(SplashUiState()) {

    init {
        runningSplashScreen()
        collectUser()
    }

    private fun collectUser() {
        viewModelScope.launch {
            getUserUseCase().collect { user ->
                setState(
                    state.value.copy(
                        user = user,
                        hasUser = user?.name.orEmpty().isNotEmpty()
                    )
                )
            }
        }
    }

    fun updateUsername(newUsername: String) {
        setState(
            state.value.copy(
                newUsername = newUsername
            )
        )
    }

    fun updateUser() {
        viewModelScope.launch {
            getUserUseCase.updateUser(
                UserSettings(
                    name = state.value.newUsername
                )
            )

            navigateToApp()
        }
    }

    fun navigateToOnboarding() {
        setState(
            state.value.copy(
                navigation = SplashNavigation.ONBOARDING
            )
        )
    }

    fun navigateToLogin() {
        setState(
            state.value.copy(
                navigation = SplashNavigation.LOGIN
            )
        )
    }

    fun navigateToApp() {
        setState(
            state.value.copy(
                navigation = SplashNavigation.APP
            )
        )
    }

    private fun runningSplashScreen() {
        viewModelScope.launch {
            setState(
                state.value.copy(
                    isLoading = true
                )
            )

            delay(2500)

            setState(
                state.value.copy(
                    isLoading = false
                )
            )

            if (state.value.hasUser) {
                navigateToApp()
            } else {
                navigateToOnboarding()
            }
        }
    }
}