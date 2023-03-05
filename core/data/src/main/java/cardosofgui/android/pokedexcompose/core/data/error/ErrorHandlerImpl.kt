package cardosofgui.android.pokedexcompose.core.data.error

import cardosofgui.android.pokedexcompose.core.error.ErrorEntity
import cardosofgui.android.pokedexcompose.core.error.ErrorHandler
import java.io.IOException

class ErrorHandlerImpl: ErrorHandler {
    override fun getError(throwable: Throwable): ErrorEntity {
        return when(throwable) {
            is IOException -> ErrorEntity.Network
            else -> { ErrorEntity.Unknown }
        }
    }

}