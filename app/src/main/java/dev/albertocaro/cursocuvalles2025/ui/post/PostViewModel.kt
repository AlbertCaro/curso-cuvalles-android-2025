package dev.albertocaro.cursocuvalles2025.ui.post

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.albertocaro.cursocuvalles2025.domain.models.Post
import dev.albertocaro.cursocuvalles2025.domain.usecases.post.DeletePostUseCase
import dev.albertocaro.cursocuvalles2025.domain.usecases.post.FindPostUseCase
import dev.albertocaro.cursocuvalles2025.domain.usecases.post.GetPostsUseCase
import dev.albertocaro.cursocuvalles2025.domain.usecases.post.SavePostUseCase
import dev.albertocaro.cursocuvalles2025.domain.usecases.post.UpdatePostUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val savePostUseCase: SavePostUseCase,
    private val getPostsUseCase: GetPostsUseCase,
    private val deletePostUseCase: DeletePostUseCase,
    private val findPostUseCase: FindPostUseCase,
    private val updatePostUseCase: UpdatePostUseCase
) : ViewModel() {
    val list = MutableLiveData(emptyList<Post>())

    val post = MutableLiveData<Post>()

    fun createPost(title: String, content: String) {
        val newPost = Post(null, title, content)

        viewModelScope.launch(Dispatchers.IO) {
            savePostUseCase(newPost)
        }
    }

    fun fetchPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            list.postValue(getPostsUseCase())
        }
    }

    fun deletePost(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            deletePostUseCase(post)
        }
    }

    fun findPost(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            post.postValue(findPostUseCase(id))
        }
    }

    fun editPost(id: Int, title: String, content: String) {
        viewModelScope.launch(Dispatchers.IO) {
            post.postValue(updatePostUseCase(id, title, content))
        }
    }
}