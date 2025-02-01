package dev.albertocaro.cursocuvalles2025.domain.models

import dev.albertocaro.cursocuvalles2025.data.database.entity.Post as EntityPost

data class Post(
    val id: Int?,
    val title: String,
    val content: String,
)

fun EntityPost.toDomain() = Post(id, title, content)