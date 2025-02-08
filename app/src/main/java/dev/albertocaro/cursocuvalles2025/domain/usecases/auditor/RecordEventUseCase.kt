package dev.albertocaro.cursocuvalles2025.domain.usecases.auditor

import dev.albertocaro.cursocuvalles2025.data.AuditorRepository
import dev.albertocaro.cursocuvalles2025.domain.models.AuditorEventType
import dev.albertocaro.cursocuvalles2025.domain.models.Event
import dev.albertocaro.cursocuvalles2025.domain.models.Module
import javax.inject.Inject

class RecordEventUseCase @Inject constructor(
    private val repository: AuditorRepository,
) {

    suspend operator fun invoke(module: Module, eventType: AuditorEventType, data: String) {
        repository.saveEvent(Event(null, module, eventType, data))
    }
}