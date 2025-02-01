package dev.albertocaro.cursocuvalles2025.domain.usecases.post

import dev.albertocaro.cursocuvalles2025.data.PostRepository
import dev.albertocaro.cursocuvalles2025.domain.models.Post
import javax.inject.Inject

class FindPostUseCase @Inject constructor(
    private val repository: PostRepository
) {

    suspend operator fun invoke(id: Int): Post {
        return repository.findPostById(id)
    }
}