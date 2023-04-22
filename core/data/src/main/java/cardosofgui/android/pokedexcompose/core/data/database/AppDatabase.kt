package cardosofgui.android.pokedexcompose.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import cardosofgui.android.pokedexcompose.core.data.database.dao.FavoriteDao
import cardosofgui.android.pokedexcompose.core.data.database.dao.PokemonDao
import cardosofgui.android.pokedexcompose.core.data.database.dao.StatsDao
import cardosofgui.android.pokedexcompose.core.data.database.dao.TypeDao
import cardosofgui.android.pokedexcompose.core.data.database.entity.FavoriteEntity
import cardosofgui.android.pokedexcompose.core.data.database.entity.PokemonEntity
import cardosofgui.android.pokedexcompose.core.data.database.entity.StatsEntity
import cardosofgui.android.pokedexcompose.core.data.database.entity.TypeEntity

@Database(
    entities = [
        PokemonEntity::class,
        TypeEntity::class,
        StatsEntity::class,
        FavoriteEntity::class
    ],
    version = 6
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun typeDao(): TypeDao
    abstract fun statsDao(): StatsDao
    abstract fun favoriteDao(): FavoriteDao
}