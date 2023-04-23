package cardosofgui.android.pokedexcompose.core.data.repository

import android.content.Context
import cardosofgui.android.pokedexcompose.core.data.datastore.proto.FilterTypeProto
import cardosofgui.android.pokedexcompose.core.data.datastore.serializer.dataStoreUser
import cardosofgui.android.pokedexcompose.core.network.model.FilterType
import cardosofgui.android.pokedexcompose.core.network.model.UserSettings
import cardosofgui.android.pokedexcompose.core.repository.UserRepository
import kotlinx.collections.immutable.mutate
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val context: Context
): UserRepository {

    private val dataStore = context.dataStoreUser

    override suspend fun getUser(): Flow<UserSettings?> {
        return dataStore.data.map {
            UserSettings(
                name = it.name,
                filterType = FilterType.valueOf(it.filterType?.name ?: FilterType.NUMBER.name)
            )
        }
    }

    override suspend fun updateUser(userSettings: UserSettings) {
        dataStore.updateData {
            it.copy(
                name = userSettings.name,
                filterType = FilterTypeProto.valueOf(it.filterType?.name ?: FilterType.NUMBER.name)
            )
        }
    }
}