package dev.albertocaro.cursocuvalles2025.ui.counter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.albertocaro.cursocuvalles2025.domain.usecases.counter.GetCounterUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CounterViewModel @Inject constructor(
    private val counterUseCase: GetCounterUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<CounterUiState>(CounterUiState.Loading)
    val uiState: StateFlow<CounterUiState> = _uiState

//    val counter = MutableLiveData<Int>()

    fun startToCount() {
        viewModelScope.launch {
            counterUseCase()
                .catch { _uiState.value = CounterUiState.Error(it.message.orEmpty()) }
                .flowOn(Dispatchers.IO)
                .collect { currentCount ->
                    _uiState.value = CounterUiState.Success(currentCount)
                }
        }
    }
}


sealed class CounterUiState {
    data object Loading : CounterUiState()
    data class Success(val counter: Int) : CounterUiState()
    data class Error(val message: String) : CounterUiState()
}