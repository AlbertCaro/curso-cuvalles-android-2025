package dev.albertocaro.cursocuvalles2025.domain.usecases.post

import dev.albertocaro.cursocuvalles2025.data.PostRepository
import dev.albertocaro.cursocuvalles2025.domain.models.AuditorEventType
import dev.albertocaro.cursocuvalles2025.domain.models.Module
import dev.albertocaro.cursocuvalles2025.domain.models.Post
import dev.albertocaro.cursocuvalles2025.domain.usecases.auditor.RecordEventUseCase
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val repository: PostRepository,
    private val recordEventUseCase: RecordEventUseCase
) {

    suspend operator fun invoke(): List<Post> {
        val posts = repository.getAllPosts()

        recordEventUseCase(Module.POST, AuditorEventType.QUERY, posts.toString())

        return posts
    }
}