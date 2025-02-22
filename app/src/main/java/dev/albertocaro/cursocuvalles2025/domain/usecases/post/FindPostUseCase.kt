package dev.albertocaro.cursocuvalles2025.domain.usecases.post

import dev.albertocaro.cursocuvalles2025.data.PostRepository
import dev.albertocaro.cursocuvalles2025.domain.models.AuditorEventType
import dev.albertocaro.cursocuvalles2025.domain.models.Module
import dev.albertocaro.cursocuvalles2025.domain.models.Post
import dev.albertocaro.cursocuvalles2025.domain.usecases.auditor.RecordEventUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FindPostUseCase @Inject constructor(
    private val repository: PostRepository,
    private val recordEventUseCase: RecordEventUseCase,
) {

    suspend operator fun invoke(id: Int): Flow<Post> {
        val post = repository.findPostById(id)

        recordEventUseCase(Module.POST, AuditorEventType.QUERY, post.toString())

        return post
    }
}