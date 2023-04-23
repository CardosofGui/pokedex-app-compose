package cardosofgui.android.pokedexcompose.core.repository

import cardosofgui.android.pokedexcompose.core.network.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUser(): Flow<User?>
    suspend fun updateUser(user: User)
}