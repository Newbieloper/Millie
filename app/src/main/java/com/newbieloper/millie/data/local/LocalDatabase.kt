package com.newbieloper.millie.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.ProvidedTypeConverter
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.newbieloper.millie.data.entity.ArticleEntity
import com.newbieloper.millie.data.entity.SourceEntity
import com.newbieloper.millie.data.local.article.ArticleDao
import java.time.ZonedDateTime

@Database(
    entities = [ArticleEntity::class],
    version = 1
)
@TypeConverters(RoomTypeConverter::class)
abstract class LocalDatabase : RoomDatabase() {

    companion object {
        private const val DB_NAME = "MillieLocalDB"

        fun create(context: Context, gson: Gson): LocalDatabase =
            Room.databaseBuilder(context, LocalDatabase::class.java, DB_NAME)
                .addTypeConverter(RoomTypeConverter(gson)).build()
    }

    abstract fun articleDao(): ArticleDao

}

@ProvidedTypeConverter
class RoomTypeConverter(
    private val gson: Gson
) {

    @TypeConverter
    fun sourceEntityToJson(value: SourceEntity): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun jsonToSourceEntity(value: String): SourceEntity {
        return gson.fromJson(value, SourceEntity::class.java)
    }

    @TypeConverter
    fun zonedDateTimeToJson(value: ZonedDateTime): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun jsonToZonedDateTime(value: String): ZonedDateTime {
        return gson.fromJson(value, ZonedDateTime::class.java)
    }
}