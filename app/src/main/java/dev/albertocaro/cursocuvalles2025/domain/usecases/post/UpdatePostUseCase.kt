package dev.albertocaro.cursocuvalles2025.domain.usecases.post

import dev.albertocaro.cursocuvalles2025.data.PostRepository
import dev.albertocaro.cursocuvalles2025.di.EditSound
import dev.albertocaro.cursocuvalles2025.domain.models.AuditorEventType
import dev.albertocaro.cursocuvalles2025.domain.models.Module
import dev.albertocaro.cursocuvalles2025.domain.models.Post
import dev.albertocaro.cursocuvalles2025.domain.usecases.auditor.RecordEventUseCase
import dev.albertocaro.cursocuvalles2025.domain.usecases.settings.PlaySoundUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdatePostUseCase @Inject constructor(
    private val repository: PostRepository,
    private val findPostUseCase: FindPostUseCase,
    private val recordEventUseCase: RecordEventUseCase,
    private val playSoundUseCase: PlaySoundUseCase,
    @EditSound private val editSound: Int
) {

    suspend operator fun invoke(id: Int, title: String, content: String): Flow<Post> {
        val post = Post(id, title, content)

        repository.updatePost(post)

        recordEventUseCase(Module.POST, AuditorEventType.UPDATE, post.toString())

        playSoundUseCase(editSound)

        return findPostUseCase(id)
    }
}