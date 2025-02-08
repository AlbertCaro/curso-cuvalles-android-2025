package dev.albertocaro.cursocuvalles2025.domain.usecases.settings

import dev.albertocaro.cursocuvalles2025.data.PreferencesRepository
import dev.albertocaro.cursocuvalles2025.domain.models.AuditorEventType
import dev.albertocaro.cursocuvalles2025.domain.models.Module
import dev.albertocaro.cursocuvalles2025.domain.usecases.auditor.RecordEventUseCase
import javax.inject.Inject

class SaveSoundOnActionUseCase @Inject constructor(
    private val repository: PreferencesRepository,
    private val recordEventUseCase: RecordEventUseCase
) {

    suspend operator fun invoke(value: Boolean) {
        repository.saveSoundOnAction(value)

        recordEventUseCase(Module.SETTINGS, AuditorEventType.INSERT, value.toString())
    }
}