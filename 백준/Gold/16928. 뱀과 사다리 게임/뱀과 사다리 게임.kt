import java.util.*

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val ladderMap = mutableMapOf<Int, Int>()
    val snakeMap = mutableMapOf<Int, Int>()
    val map = mutableMapOf<Int, Int>()

    repeat(n) {
        val (x, y) = readln().split(" ").map { it.toInt() }
        map[x] = y
    }

    repeat(m) {
        val (u, v) = readln().split(" ").map { it.toInt() }
        map[u] = v
    }

    bfs(map)
}

private fun bfs(
    map: Map<Int, Int>,
) {
    val visited = BooleanArray(101)
    visited[1] = true

    val queue: Queue<Pair<Int, Int>> = LinkedList()
    queue.add(1 to 0)

    while (queue.isNotEmpty()) {
        val (next, count) = queue.poll()

        if (next == 100) {
            println(count)
            break
        }

        for (i in 1..6) {
            val nextNum = next + i
            if (nextNum <= 100 && !visited[nextNum]) {
                visited[nextNum] = true
                if (map.containsKey(nextNum)) {
                    visited[map[nextNum]!!] = true
                    queue.add(map[nextNum]!! to count + 1)
                } else {
                    queue.add(nextNum to count + 1)
                }
            }
        }
    }
}