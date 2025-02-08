package dev.albertocaro.cursocuvalles2025.ui.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.albertocaro.cursocuvalles2025.domain.models.Settings
import dev.albertocaro.cursocuvalles2025.domain.usecases.settings.GetSettingsUseCase
import dev.albertocaro.cursocuvalles2025.domain.usecases.settings.SaveSoundOnActionUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val saveSoundOnActionUseCase: SaveSoundOnActionUseCase,
    private val getSettingsUseCase: GetSettingsUseCase
) : ViewModel() {

    val settings = MutableLiveData<Settings>()

    fun togglePlaySounds(value: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            saveSoundOnActionUseCase(value)
        }
    }

    fun fetchSettings() {
        viewModelScope.launch(Dispatchers.IO) {
            settings.postValue(getSettingsUseCase())
        }
    }
}