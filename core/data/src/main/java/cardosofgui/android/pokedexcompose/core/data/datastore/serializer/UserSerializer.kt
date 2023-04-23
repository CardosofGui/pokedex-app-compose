package cardosofgui.android.pokedexcompose.core.data.datastore.serializer

import android.content.Context
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import cardosofgui.android.pokedexcompose.core.data.datastore.proto.UserProto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object UserSerializer: Serializer<UserProto> {
    override val defaultValue: UserProto
        get() = UserProto()

    override suspend fun readFrom(input: InputStream): UserProto {
        return try {
            Json.decodeFromString(
                deserializer = UserProto.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: UserProto, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(
                    serializer = UserProto.serializer(),
                    value = t
                ).encodeToByteArray()
            )
        }
    }
}

internal val Context.dataStoreUser by dataStore(
    fileName = "user.pb",
    serializer = UserSerializer
)