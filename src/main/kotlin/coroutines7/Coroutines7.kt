import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun calculatePrices(): Flow<Int> = flow { // flow builder
    for (i in 1..5) {
        delay(100) // pretend we are doing some db call to get a price
        emit(i) // emit next value
    }
}

fun main() = runBlocking {
    // Launch a concurrent coroutine to check if the main thread is blocked
    launch {
        for (k in 1..5) {
            println("I'm not blocked $k")
            delay(100)
        }
    }
    // Collect the flow
    calculatePrices().collect { value -> println(value) }
}
