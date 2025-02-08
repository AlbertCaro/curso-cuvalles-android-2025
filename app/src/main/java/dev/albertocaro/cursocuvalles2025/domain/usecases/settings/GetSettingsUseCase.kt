package dev.albertocaro.cursocuvalles2025.domain.usecases.settings

import dev.albertocaro.cursocuvalles2025.data.PreferencesRepository
import dev.albertocaro.cursocuvalles2025.domain.models.AuditorEventType
import dev.albertocaro.cursocuvalles2025.domain.models.Module
import dev.albertocaro.cursocuvalles2025.domain.models.Settings
import dev.albertocaro.cursocuvalles2025.domain.usecases.auditor.RecordEventUseCase
import javax.inject.Inject

class GetSettingsUseCase @Inject constructor(
    private val repository: PreferencesRepository,
    private val recordEventUseCase: RecordEventUseCase
) {

    suspend operator fun invoke(): Settings {
        val settings = repository.getSettings()

        recordEventUseCase(Module.SETTINGS, AuditorEventType.QUERY, settings.toString())

        return settings
    }
}