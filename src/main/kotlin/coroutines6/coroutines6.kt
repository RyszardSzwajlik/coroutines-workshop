import kotlinx.coroutines.runBlocking
import java.time.LocalTime

fun main() = runBlocking {
    println("${LocalTime.now()}")
    // val helloDeferred = ... // todo: function that Thread.sleep(2000) and returns "Hello"

    // val worldDeferred = ... // todo: function that Thread.sleep(2000) and returns "world"

    // println(helloDeferred + worldDeferred) // todo: print it
    println("${LocalTime.now()}")
}
