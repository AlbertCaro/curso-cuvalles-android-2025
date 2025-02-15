package dev.albertocaro.cursocuvalles2025.data

import dev.albertocaro.cursocuvalles2025.data.preferences.PreferencesManager
import dev.albertocaro.cursocuvalles2025.domain.models.Settings
import javax.inject.Inject

class PreferencesRepository @Inject constructor(
    private val preferencesManager: PreferencesManager
) {

    fun saveSoundOnAction(value: Boolean) {
        preferencesManager.saveSoundOnAction(value)
    }

    fun mustSoundOnAction(): Boolean {
        return preferencesManager.getSoundOnAction()
    }

    fun getSettings(): Settings {
        return Settings(mustSoundOnAction())
    }
}