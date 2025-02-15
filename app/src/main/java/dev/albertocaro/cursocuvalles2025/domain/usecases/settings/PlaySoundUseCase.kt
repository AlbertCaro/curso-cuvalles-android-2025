package dev.albertocaro.cursocuvalles2025.domain.usecases.settings

import dev.albertocaro.cursocuvalles2025.core.AudioHelper
import dev.albertocaro.cursocuvalles2025.data.PreferencesRepository
import javax.inject.Inject

class PlaySoundUseCase @Inject constructor(
    private val audioHelper: AudioHelper,
    private val repository: PreferencesRepository
) {

    operator fun invoke(sound: Int) {
        if (!repository.mustSoundOnAction()) return

        audioHelper.playSound(sound)
    }
}