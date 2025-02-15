package dev.albertocaro.cursocuvalles2025.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CounterRepository @Inject constructor() {

    fun count(): Flow<Int> = flow {
        var counter = 0

        while (true) {
            delay(3000)

            if (counter > 10) {
                throw Exception("Sobrepasamos 10")
            }

            emit(counter)
            counter++
        }
    }
}