package cardosofgui.android.pokedexcompose.core.data.network.utils

import kotlinx.serialization.Serializable
@Serializable
data class ResultGeneric<T>(
    val count: Long? = null,
    val next: String? = null,
    val previous: String? = null,
    val results: List<T>? = null
)