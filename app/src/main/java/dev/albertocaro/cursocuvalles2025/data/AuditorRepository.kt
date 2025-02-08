package dev.albertocaro.cursocuvalles2025.data

import dev.albertocaro.cursocuvalles2025.data.database.dao.AuditorDao
import dev.albertocaro.cursocuvalles2025.data.database.entity.toEntity
import dev.albertocaro.cursocuvalles2025.domain.models.Event
import dev.albertocaro.cursocuvalles2025.domain.models.toDomain
import javax.inject.Inject

class AuditorRepository @Inject constructor(
    private val auditorDao: AuditorDao
) {

    suspend fun saveEvent(event: Event) {
        auditorDao.insertEvent(event.toEntity())
    }

    suspend fun fetchAll(): List<Event> {
        return auditorDao.getAllEvents().map { it.toDomain() }
    }
}