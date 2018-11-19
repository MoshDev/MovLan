package space.ersan.movlan.app.builder

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import dagger.Module
import dagger.Provides
import space.ersan.movlan.ext.MovieDateParser
import java.util.Date

@Module
class GsonModule {

  @Provides
  @AppScope
  fun provideGson(): Gson {
    val builder = GsonBuilder()
    builder.setLenient()

    builder.registerTypeAdapter(Date::class.java, JsonDeserializer<Date> { json, _, _ ->
      when (json.asJsonPrimitive.isString) {
        true -> MovieDateParser.parseJsonDate(json.asJsonPrimitive.asString)
        else -> null
      }
    })
    return builder.create()
  }
}