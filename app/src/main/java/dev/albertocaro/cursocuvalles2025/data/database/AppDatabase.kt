package dev.albertocaro.cursocuvalles2025.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.albertocaro.cursocuvalles2025.data.database.converter.DateConverter
import dev.albertocaro.cursocuvalles2025.data.database.dao.AuditorDao
import dev.albertocaro.cursocuvalles2025.data.database.dao.PostDao
import dev.albertocaro.cursocuvalles2025.data.database.entity.AuditEvent
import dev.albertocaro.cursocuvalles2025.data.database.entity.Post

@Database(entities = [Post::class, AuditEvent::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao

    abstract fun auditDao(): AuditorDao
}