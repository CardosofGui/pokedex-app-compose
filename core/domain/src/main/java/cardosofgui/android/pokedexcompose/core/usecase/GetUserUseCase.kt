package cardosofgui.android.pokedexcompose.core.usecase

import cardosofgui.android.pokedexcompose.core.network.model.UserSettings
import cardosofgui.android.pokedexcompose.core.repository.UserRepository
import kotlinx.coroutines.flow.first

class GetUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() = userRepository.getUser()

    suspend fun updateUser(userSettings: UserSettings) = userRepository.updateUser(userSettings)
}