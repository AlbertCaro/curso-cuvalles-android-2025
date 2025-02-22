package dev.albertocaro.cursocuvalles2025.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.albertocaro.cursocuvalles2025.data.database.entity.Post
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) // INSERT INTO post VALUES (id, title, content)
    suspend fun insertPost(post: Post)

    @Update
    suspend fun updatePost(post: Post)

    @Query("SELECT * FROM posts")
    fun getAllPosts(): Flow<List<Post>>

    @Delete // DELETE FROM posts WHERE id = 1
    suspend fun deletePost(post: Post)

    @Query("SELECT * FROM posts WHERE id = :id LIMIT 1")
    fun findPost(id: Int): Flow<Post>
}