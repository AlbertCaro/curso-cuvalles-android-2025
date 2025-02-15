package dev.albertocaro.cursocuvalles2025.domain.usecases.post

import dev.albertocaro.cursocuvalles2025.data.PostRepository
import dev.albertocaro.cursocuvalles2025.di.DeleteSound
import dev.albertocaro.cursocuvalles2025.domain.models.AuditorEventType
import dev.albertocaro.cursocuvalles2025.domain.models.Module
import dev.albertocaro.cursocuvalles2025.domain.models.Post
import dev.albertocaro.cursocuvalles2025.domain.usecases.auditor.RecordEventUseCase
import dev.albertocaro.cursocuvalles2025.domain.usecases.settings.PlaySoundUseCase
import javax.inject.Inject

class DeletePostUseCase @Inject constructor(
    private val repository: PostRepository,
    private val recordEventUseCase: RecordEventUseCase,
    private val playSoundUseCase: PlaySoundUseCase,
    @DeleteSound private val deleteSound: Int
) {

    suspend operator fun invoke(post: Post) {
        repository.deletePost(post)

        recordEventUseCase(Module.POST, AuditorEventType.DELETE, post.toString())

        playSoundUseCase(deleteSound)
    }
}