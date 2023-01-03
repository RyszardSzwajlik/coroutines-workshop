import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.time.LocalTime

fun main() = runBlocking {
    println("${LocalTime.now()}")
    val helloDeferred = async(Dispatchers.IO) {
        Thread.sleep(2000)
        return@async "hello"
    }

    val worldDeferred = async(Dispatchers.IO) {
        Thread.sleep(2000)
        return@async "world"
    }

    println("${helloDeferred.await()} ${worldDeferred.await()}")
    println("${LocalTime.now()}")
}
