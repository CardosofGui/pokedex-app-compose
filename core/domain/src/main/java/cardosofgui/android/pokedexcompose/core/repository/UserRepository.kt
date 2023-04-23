package cardosofgui.android.pokedexcompose.core.repository

import cardosofgui.android.pokedexcompose.core.network.model.UserSettings
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUser(): Flow<UserSettings?>
    suspend fun updateUser(userSettings: UserSettings)
}