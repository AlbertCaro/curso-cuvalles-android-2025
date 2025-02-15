package dev.albertocaro.cursocuvalles2025.data

import dev.albertocaro.cursocuvalles2025.data.api.model.toApi
import dev.albertocaro.cursocuvalles2025.data.api.service.PostApiService
import dev.albertocaro.cursocuvalles2025.data.database.dao.PostDao
import dev.albertocaro.cursocuvalles2025.data.database.entity.toEntity
import dev.albertocaro.cursocuvalles2025.di.NetworkState
import dev.albertocaro.cursocuvalles2025.domain.models.Post
import dev.albertocaro.cursocuvalles2025.domain.models.toDomain
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val postDao: PostDao,
    private val apiService: PostApiService,
    @NetworkState private val isConnected: Boolean
) {

    suspend fun savePost(post: Post) {
        if (isConnected) {
            apiService.createPost(post.toApi())
        }

        postDao.insertPost(post.toEntity())
    }

    suspend fun updatePost(post: Post) {
        if (isConnected) {
            apiService.editPost(post.id!!, post.toApi())
        }

        postDao.updatePost(post.toEntity())
    }

    suspend fun getAllPosts(): List<Post> {
        if (isConnected) {
            return apiService.getPosts().map { it.toDomain() }
        }

        return postDao.getAllPosts().map { it.toDomain() }
    }

    suspend fun deletePost(post: Post) {
        if (isConnected) {
            apiService.deletePost(post.id!!)
        }

        postDao.deletePost(post.toEntity())
    }

    suspend fun findPostById(id: Int): Post {
        if (isConnected) {
            return apiService.getPost(id).toDomain()
        }

        return postDao.findPost(id).toDomain()
    }
}