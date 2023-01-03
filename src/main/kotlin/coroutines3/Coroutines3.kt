package coroutines3

fun main() {
    val sequence = sequence {
        println("one")
        yield('a')

        println("two")
        yield('b')

        println("three")
        yield('c')
    }

    for (value in sequence) {
        println("$value")
    }
}
