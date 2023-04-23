package cardosofgui.android.pokedexcompose.core.usecase

import cardosofgui.android.pokedexcompose.core.network.model.User
import cardosofgui.android.pokedexcompose.core.repository.UserRepository

class GetUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() = userRepository.getUser()

    suspend fun updateUser(user: User) = userRepository.updateUser(user)

}