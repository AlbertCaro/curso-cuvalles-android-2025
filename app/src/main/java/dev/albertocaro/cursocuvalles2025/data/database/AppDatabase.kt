package dev.albertocaro.cursocuvalles2025.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.albertocaro.cursocuvalles2025.data.database.dao.PostDao
import dev.albertocaro.cursocuvalles2025.data.database.entity.Post

@Database(entities = [Post::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}