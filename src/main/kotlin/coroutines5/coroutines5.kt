import kotlinx.coroutines.delay

fun main() {
    suspend fun task(taskId: Int) {
        println("start task $taskId in Thread ${Thread.currentThread().name}")
        delay(100)
        println("end task $taskId in Thread ${Thread.currentThread().name}")
    }

    // code goes here
    println("asd")
}