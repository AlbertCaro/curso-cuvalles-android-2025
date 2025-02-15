package dev.albertocaro.cursocuvalles2025.domain.usecases.counter

import dev.albertocaro.cursocuvalles2025.data.CounterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCounterUseCase @Inject constructor(
    private val repository: CounterRepository
) {

    operator fun invoke(): Flow<Int> {
        return repository.count()
    }
}