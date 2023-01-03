package coroutines1

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

fun main() = runBlocking {

    repeat(20000) {
        launch {
            delay(1000)
            println("world ${Thread.currentThread().name}")
        }
    }

    repeat(20000) {
        thread {
            Thread.sleep(1000)
            println("world ${Thread.currentThread().name}")
        }
    }
    println("hello ${Thread.currentThread().name}")

}

