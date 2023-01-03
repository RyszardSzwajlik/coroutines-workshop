import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

fun main() {
    suspend fun task(taskId: Int) {
        println("start task $taskId in Thread ${Thread.currentThread().name}")
        delay(100)
        println("end task $taskId in Thread ${Thread.currentThread().name}")
    }

    // code goes here
//    val context = Dispatchers.Unconfined
//    val context = newSingleThreadContext("MyOwnThread")
    val context = Executors.newFixedThreadPool(4).asCoroutineDispatcher()
    runBlocking {
        repeat(10) {
            launch(context) { task(it) }
        }
    }
    println("asd")
}