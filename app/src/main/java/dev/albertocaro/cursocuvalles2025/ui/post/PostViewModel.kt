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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
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

    private val _listUiState = MutableStateFlow<PostListUiState>(PostListUiState.Loading)
    val listUiState: StateFlow<PostListUiState> = _listUiState

    private val _viewUiState = MutableStateFlow<PostViewUiState>(PostViewUiState.Loading)
    val viewUiState: StateFlow<PostViewUiState> = _viewUiState

//    val list = MutableLiveData(emptyList<Post>())
//    val post = MutableLiveData<Post>()

    fun createPost(title: String, content: String) {
        val newPost = Post(null, title, content)

        viewModelScope.launch(Dispatchers.IO) {
            savePostUseCase(newPost)
        }
    }

    fun fetchPosts() {
        viewModelScope.launch {
            getPostsUseCase()
                .catch { _listUiState.value = PostListUiState.Error(it.message.orEmpty()) }
                .flowOn(Dispatchers.IO)
                .collect { posts ->
                    _listUiState.value = PostListUiState.Success(posts)
                }
        }
    }

    fun deletePost(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            deletePostUseCase(post)
        }
    }

    fun findPost(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            findPostUseCase(id)
                .catch { _viewUiState.value = PostViewUiState.Error(it.message.orEmpty()) }
                .flowOn(Dispatchers.IO)
                .collect { post ->
                    _viewUiState.value = PostViewUiState.Success(post)
                }
        }
    }

    fun editPost(id: Int, title: String, content: String) {
        viewModelScope.launch(Dispatchers.IO) {
            updatePostUseCase(id, title, content)
                .catch { _viewUiState.value = PostViewUiState.Error(it.message.orEmpty()) }
                .flowOn(Dispatchers.IO)
                .collect { post ->
                    _viewUiState.value = PostViewUiState.Success(post)
                }
        }
    }
}

sealed class PostListUiState {
    data object Loading: PostListUiState()
    data class Success(val list: List<Post>) : PostListUiState()
    data class Error(val message: String) : PostListUiState()
}

sealed class PostViewUiState {
    data object Loading: PostViewUiState()
    data class Success(val post: Post) : PostViewUiState()
    data class Error(val message: String) : PostViewUiState()
}


