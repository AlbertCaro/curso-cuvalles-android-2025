package dev.albertocaro.cursocuvalles2025.domain.models

import dev.albertocaro.cursocuvalles2025.data.database.entity.Post as EntityPost
import dev.albertocaro.cursocuvalles2025.data.api.model.Post as ApiPost

data class Post(
    val id: Int?,
    val title: String,
    val content: String,
)

fun EntityPost.toDomain() = Post(id, title, content)

fun ApiPost.toDomain() = Post(id, title, content)