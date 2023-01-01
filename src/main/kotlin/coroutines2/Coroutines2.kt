package coroutines2

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job1 = launch { // CoroutineScope
        delay(500)
        println("world")

        val job2 = launch {
            delay(1000)
            println("from coroutines")
        }
        job2.invokeOnCompletion { println("job2 completed") }
    }
    job1.invokeOnCompletion { println("job1 completed") }
    println("hello")
}
