import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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
//            println("I'm not blocked $k")
            delay(100)
        }
    }
    // Collect the flow
    calculatePrices()
        .filter { it % 2 == 0 }
        .transform {
            emit("The original price is $it")
            emit("Doubled price is ${it * 2}")
        }
        .collect { value -> println(value) }
}
