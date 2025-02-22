package dev.albertocaro.cursocuvalles2025.data

import dev.albertocaro.cursocuvalles2025.data.api.model.toApi
import dev.albertocaro.cursocuvalles2025.data.api.service.PostApiService
import dev.albertocaro.cursocuvalles2025.data.database.dao.PostDao
import dev.albertocaro.cursocuvalles2025.data.database.entity.toEntity
import dev.albertocaro.cursocuvalles2025.di.NetworkState
import dev.albertocaro.cursocuvalles2025.domain.models.Post
import dev.albertocaro.cursocuvalles2025.domain.models.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val postDao: PostDao,
    private val apiService: PostApiService,
    @NetworkState private val networkState: Flow<Boolean>
) {

    suspend fun savePost(post: Post) {
        val isConnected = networkState.firstOrNull()

        if (isConnected == true) {
            apiService.createPost(post.toApi())
        }

        postDao.insertPost(post.toEntity())
    }

    suspend fun updatePost(post: Post) {
        val isConnected = networkState.firstOrNull()

        if (isConnected == true) {
            apiService.editPost(post.id!!, post.toApi())
        }

        postDao.updatePost(post.toEntity())
    }

    suspend fun deletePost(post: Post) {
        val isConnected = networkState.firstOrNull()

        if (isConnected == true) {
            apiService.deletePost(post.id!!)
        }

        postDao.deletePost(post.toEntity())
    }

    fun getAllPosts(): Flow<List<Post>> =
        networkState.combine(postDao.getAllPosts()) { isConnected, posts ->
            if (isConnected) {
                apiService.getPosts().map { it.toDomain() }
            } else {
                posts.map { it.toDomain() }
            }
        }

    fun findPostById(id: Int): Flow<Post> = networkState.mapLatest { isConnected ->
        if (isConnected) {
            apiService.getPost(id).toDomain()
        } else {
            postDao.findPost(id).first().toDomain()
        }
    }
}