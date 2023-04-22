package cardosofgui.android.pokedexcompose.core.data.repository

import cardosofgui.android.pokedexcompose.core.data.database.dao.FavoriteDao
import cardosofgui.android.pokedexcompose.core.data.database.dao.PokemonDao
import cardosofgui.android.pokedexcompose.core.data.database.dao.StatsDao
import cardosofgui.android.pokedexcompose.core.data.database.entity.FavoriteEntity
import cardosofgui.android.pokedexcompose.core.data.database.entity.PokemonEntity
import cardosofgui.android.pokedexcompose.core.data.database.entity.StatsEntity
import cardosofgui.android.pokedexcompose.core.network.model.Pokemon
import cardosofgui.android.pokedexcompose.core.network.service.PokemonApiClient
import cardosofgui.android.pokedexcompose.core.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PokemonRepositoryImpl(
    private val pokemonApiClient: PokemonApiClient,
    private val pokemonDao: PokemonDao,
    private val statsDao: StatsDao,
    private val favoriteDao: FavoriteDao
): PokemonRepository {
    override suspend fun getPokemonById(id: Long): Flow<Pokemon?> {
        val pokemon = pokemonApiClient.getPokemon(pokemonId = id)

        val idResult = pokemonDao.insert(PokemonEntity().domainToEntity(pokemon))

        pokemon.stats?.forEach {
            statsDao.insert(StatsEntity().domainToEntity(it, idResult))
        }

        val favoriteStatus = favoriteDao.queryFavorite(idResult) != null

        return pokemonDao.queryById(idResult).map {
            it?.toDomain()?.copy(
                favoriteStatus = favoriteStatus
            )
        }
    }
    override suspend fun getPokemonList(limit: Long, offset: Long): Flow<List<Pokemon>> {
        val pokemonList = pokemonApiClient.getPokemonList(limit = limit, offset = offset)

        pokemonList?.forEach {
            pokemonDao.insert(PokemonEntity().domainToEntity(it))
        }

        return pokemonDao.queryAll().map { pokemonEntityList ->
            pokemonEntityList.map { pokemonEntity ->
                pokemonEntity.toDomain()
            }
        }
    }

    override fun getFavoritePokemonList(): Flow<List<Pokemon>> {
        return pokemonDao.queryAllFavorites().map { pokemonEntityList ->
            pokemonEntityList.map { pokemonEntity ->
                pokemonEntity.toDomain()
            }
        }
    }
    override suspend fun addFavoritePokemon(pokemon: Pokemon?) {
        favoriteDao.insertFavorite(
            FavoriteEntity(
                pokemonId = pokemon?.id ?: throw Exception("Pokemon is null"),
            )
        )
    }
    override suspend fun removeFavoritePokemon(pokemon: Pokemon?) {
        favoriteDao.deleteFavorite(
            pokemonId = pokemon?.id ?: throw Exception("Pokemon is null"),
        )
    }
}