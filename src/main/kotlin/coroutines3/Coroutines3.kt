package coroutines3

fun main() {
    val sequence = buildList { // sequence
        println("one")
        add('a') // yield

        println("two")
        add('b')

        println("three")
        add('c')
    }

    for (value in sequence) {
        println("$value")
    }
}
