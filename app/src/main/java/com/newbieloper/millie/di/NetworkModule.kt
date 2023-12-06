package com.newbieloper.millie.di

import com.google.gson.*
import com.newbieloper.millie.BuildConfig
import com.newbieloper.millie.core.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val BASE_URL by lazy {
        if (BuildConfig.DEBUG) Constants.Network.DEV_URL else Constants.Network.PROD_URL
    }

    @Provides
    fun provideGson(
        zonedDateTimeTypeAdapter: ZonedDateTimeTypeAdapter
    ): Gson =
        GsonBuilder()
            .registerTypeAdapter(ZonedDateTime::class.java, zonedDateTimeTypeAdapter)
            .create()

    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .writeTimeout(45, TimeUnit.SECONDS)
            .readTimeout(45, TimeUnit.SECONDS)
            .connectTimeout(45, TimeUnit.SECONDS)
            .addNetworkInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()

    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
}

/**
 * retrofit adapter 로 ZonedDateTime 을 json 상태로 serialize, deserialize 해준다.
 */
class ZonedDateTimeTypeAdapter @Inject constructor() : JsonSerializer<ZonedDateTime>, JsonDeserializer<ZonedDateTime> {
    override fun serialize(src: ZonedDateTime, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        return JsonPrimitive(src.toString())
    }

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): ZonedDateTime? {
        val jsonPrimitive = json.asJsonPrimitive
        try {
            if (jsonPrimitive.isString) {
                return ZonedDateTime.parse(jsonPrimitive.asString, DateTimeFormatter.ISO_ZONED_DATE_TIME)
            }

            if (jsonPrimitive.isNumber) {
                return ZonedDateTime.ofInstant(Instant.ofEpochMilli(jsonPrimitive.asLong), ZoneId.systemDefault())
            }
        } catch (e: RuntimeException) {
            throw JsonParseException("Unable to parse ZonedDateTime", e)
        }
        throw JsonParseException("Unable to parse ZonedDateTime")
    }
}