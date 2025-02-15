package dev.albertocaro.cursocuvalles2025.data.api.model

import com.google.gson.annotations.SerializedName
import dev.albertocaro.cursocuvalles2025.domain.models.Post as DomainPost

data class Post (
    @SerializedName("id") val id: Int?,
    @SerializedName("title") val title: String,
    @SerializedName("body") val content: String,
    @SerializedName("userId") val userId: Int = 1,
)

fun DomainPost.toApi(): Post = Post(id, title, content)