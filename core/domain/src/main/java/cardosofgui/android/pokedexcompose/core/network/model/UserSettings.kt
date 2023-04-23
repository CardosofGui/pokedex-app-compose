package cardosofgui.android.pokedexcompose.core.network.model

data class UserSettings(
    val name: String? = null,
    val filterType: FilterType? = null
)

enum class FilterType {
    NAME, NUMBER, FAVORITE
}
