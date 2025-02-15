package dev.albertocaro.cursocuvalles2025.domain.usecases.post

import dev.albertocaro.cursocuvalles2025.data.PostRepository
import dev.albertocaro.cursocuvalles2025.di.CreateSound
import dev.albertocaro.cursocuvalles2025.domain.models.AuditorEventType
import dev.albertocaro.cursocuvalles2025.domain.models.Module
import dev.albertocaro.cursocuvalles2025.domain.models.Post
import dev.albertocaro.cursocuvalles2025.domain.usecases.auditor.RecordEventUseCase
import dev.albertocaro.cursocuvalles2025.domain.usecases.settings.PlaySoundUseCase
import javax.inject.Inject

class SavePostUseCase @Inject constructor(
    private val repository: PostRepository,
    private val recordEventUseCase: RecordEventUseCase,
    private val playSoundUseCase: PlaySoundUseCase,
    @CreateSound private val createSound: Int
) {

    suspend operator fun invoke(post: Post) {
        repository.savePost(post)

        recordEventUseCase(Module.POST, AuditorEventType.INSERT, post.toString())

        playSoundUseCase(createSound)
    }
}