package cardosofgui.android.pokedexcompose.core.data.datastore.proto

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.serialization.Serializable

@Serializable
data class UserSettingsProto(
    val name: String? = null,
    val filterType: FilterTypeProto? = null
)

@Serializable
enum class FilterTypeProto {
    NAME, NUMBER, FAVORITE
}