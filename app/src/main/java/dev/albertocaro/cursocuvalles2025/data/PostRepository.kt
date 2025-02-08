package dev.albertocaro.cursocuvalles2025.data

import dev.albertocaro.cursocuvalles2025.data.database.dao.PostDao
import dev.albertocaro.cursocuvalles2025.data.database.entity.toEntity
import dev.albertocaro.cursocuvalles2025.domain.models.Post
import dev.albertocaro.cursocuvalles2025.domain.models.toDomain
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val postDao: PostDao
) {

    suspend fun savePost(post: Post) {
        postDao.insertPost(post.toEntity())
    }

    suspend fun updatePost(post: Post) {
        postDao.updatePost(post.toEntity())
    }

    suspend fun getAllPosts(): List<Post> {
        return postDao.getAllPosts().map { it.toDomain() }
    }

    suspend fun deletePost(post: Post) {
        postDao.deletePost(post.toEntity())
    }

    suspend fun findPostById(id: Int): Post {
        return postDao.findPost(id).toDomain()
    }
}