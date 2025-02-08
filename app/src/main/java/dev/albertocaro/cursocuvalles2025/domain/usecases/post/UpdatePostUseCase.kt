package dev.albertocaro.cursocuvalles2025.domain.usecases.post

import dev.albertocaro.cursocuvalles2025.data.PostRepository
import dev.albertocaro.cursocuvalles2025.domain.models.AuditorEventType
import dev.albertocaro.cursocuvalles2025.domain.models.Module
import dev.albertocaro.cursocuvalles2025.domain.models.Post
import dev.albertocaro.cursocuvalles2025.domain.usecases.auditor.RecordEventUseCase
import javax.inject.Inject

class UpdatePostUseCase @Inject constructor(
    private val repository: PostRepository,
    private val findPostUseCase: FindPostUseCase,
    private val recordEventUseCase: RecordEventUseCase
) {

    suspend operator fun invoke(id: Int, title: String, content: String): Post {
        val post = Post(id, title, content)

        repository.updatePost(post)

        recordEventUseCase(Module.POST, AuditorEventType.UPDATE, post.toString())

        return findPostUseCase(id)
    }
}