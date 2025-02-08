package dev.albertocaro.cursocuvalles2025.domain.models

import dev.albertocaro.cursocuvalles2025.data.database.entity.AuditEvent
import java.util.Date

data class Event (
    val id: Int?,

    val module: Module,

    val eventType: AuditorEventType,

    val data: String,

    val createdAt: Date = Date()
)

fun AuditEvent.toDomain() = Event(id, module, eventType, data, createdAt)
