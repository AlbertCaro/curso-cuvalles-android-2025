package dev.albertocaro.cursocuvalles2025.core

import android.content.Context
import android.media.MediaPlayer
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AudioHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun playSound(sound: Int) {
        val mediaPlayer: MediaPlayer = MediaPlayer.create(context, sound)

        mediaPlayer.start()
    }
}