package dev.albertocaro.cursocuvalles2025.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.albertocaro.cursocuvalles2025.domain.models.Post as DomainPost

@Entity(tableName = "posts")
data class Post(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    val title: String,

    val content: String
)

fun DomainPost.toEntity() = Post(id, title, content)
