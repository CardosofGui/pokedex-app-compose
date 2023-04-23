package cardosofgui.android.pokedexcompose.core.data.repository

import android.content.Context
import cardosofgui.android.pokedexcompose.core.data.datastore.serializer.dataStoreUser
import cardosofgui.android.pokedexcompose.core.network.model.User
import cardosofgui.android.pokedexcompose.core.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val context: Context
): UserRepository {

    private val dataStore = context.dataStoreUser

    override suspend fun getUser(): Flow<User?> {
        return dataStore.data.map {
            User(
                name = it.name
            )
        }
    }

    override suspend fun updateUser(user: User) {
        dataStore.updateData {
            it.copy(
                name = user.name
            )
        }
    }
}