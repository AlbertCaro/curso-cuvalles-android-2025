package dev.albertocaro.cursocuvalles2025.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.albertocaro.cursocuvalles2025.domain.models.AuditorEventType
import dev.albertocaro.cursocuvalles2025.domain.models.Event
import dev.albertocaro.cursocuvalles2025.domain.models.Module
import java.util.Date

@Entity(tableName = "auditor")
data class AuditEvent (
    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    val module: Module,

    val eventType: AuditorEventType,

    val data: String,

    val createdAt: Date = Date()
)

fun Event.toEntity() = AuditEvent(id, module, eventType, data, createdAt)