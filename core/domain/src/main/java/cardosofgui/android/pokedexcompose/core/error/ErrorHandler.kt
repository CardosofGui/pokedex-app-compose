package cardosofgui.android.pokedexcompose.core.error
interface ErrorHandler {
    fun getError(throwable: Throwable): ErrorEntity
}