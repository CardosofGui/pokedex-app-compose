package cardosofgui.android.pokedexcompose.core.error

sealed class ErrorEntity {
    object Network : ErrorEntity()
    object Unknown : ErrorEntity()
}