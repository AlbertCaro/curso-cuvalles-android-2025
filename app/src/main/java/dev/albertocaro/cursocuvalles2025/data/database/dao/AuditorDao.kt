package dev.albertocaro.cursocuvalles2025.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.albertocaro.cursocuvalles2025.data.database.entity.AuditEvent


@Dao
interface AuditorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(event: AuditEvent)

    @Query("SELECT * FROM auditor")
    suspend fun getAllEvents(): List<AuditEvent>
}