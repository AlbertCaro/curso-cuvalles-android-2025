package dev.albertocaro.cursocuvalles2025.data.preferences

import android.content.SharedPreferences
import javax.inject.Inject


class PreferencesManager @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun saveSoundOnAction(value: Boolean) {
        sharedPreferences.edit().putBoolean(SOUND_ON_ACTION, value).apply()
    }

    fun getSoundOnAction(): Boolean {
        return sharedPreferences.getBoolean(SOUND_ON_ACTION, true)
    }

    companion object {
        private const val SOUND_ON_ACTION = "sound_on_action"
    }
}