package dev.albertocaro.cursocuvalles2025.data.api.service

import dev.albertocaro.cursocuvalles2025.data.api.model.Post
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PostApiService {
    @GET("/posts")
    suspend fun getPosts(): List<Post>

    @GET("/posts/{id}")
    suspend fun getPost(@Path("id") id: Int): Post

    @POST("/posts")
    suspend fun createPost(@Body post: Post)

    @PUT("/posts/{id}")
    suspend fun editPost(@Path("id") id: Int, @Body post: Post)

    @DELETE("/posts/{id}")
    suspend fun deletePost(@Path("id") id: Int)
}