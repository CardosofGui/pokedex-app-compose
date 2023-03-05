package cardosofgui.android.pokedexcompose.core.data.di

import cardosofgui.android.pokedexcompose.core.data.error.ErrorHandlerImpl
import cardosofgui.android.pokedexcompose.core.error.ErrorHandler
import org.koin.dsl.module

val errorModules = module {
    single <ErrorHandler>{
        ErrorHandlerImpl()
    }
}