import java.util.*

var result = -1
fun main() {
    val (a, b) = readln().split(" ").map { it.toInt() }

    bfs(a, b)
    println(result)
}

private fun bfs(
    start: Int,
    end: Int
) {
    val queue: Queue<Pair<Int, Int>> = LinkedList()
    queue.add(start to 1)

    while (queue.isNotEmpty()) {
        val (num, count) = queue.poll()

        if (num == end) {
            result = count
            break
        }

        if (num.toLong() * 2 <= Int.MAX_VALUE) {
            val multiple = num * 2
            queue.add(multiple to count + 1)
        }

        if ((num.toString() + "1").toLong() <= Int.MAX_VALUE) {
            val plus = (num.toString() + "1").toInt()
            queue.add(plus to count + 1)
        }
    }
}