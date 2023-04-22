package cardosofgui.android.pokedexcompose.core.data.di

import androidx.room.Room
import cardosofgui.android.pokedexcompose.core.data.database.AppDatabase
import org.koin.dsl.module

const val DATABASE_NAME = "pokedex.db"

val databaseModules = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    factory { get<AppDatabase>().pokemonDao() }
    factory { get<AppDatabase>().typeDao() }
    factory { get<AppDatabase>().statsDao() }
    factory { get<AppDatabase>().favoriteDao() }
}