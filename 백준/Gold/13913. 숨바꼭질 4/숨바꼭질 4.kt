import java.util.*

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    if (n == k) {
        println(0)
        println(n)
    } else {
        bfs(n, k)
    }
}

private fun bfs(
    start: Int,
    end: Int
) {
    val time = IntArray(100001)
    val visited = IntArray(100001)
    time[start] = 0
    visited[start] = 0

    val queue: Queue<Int> = LinkedList()
    queue.add(start)

    while (queue.isNotEmpty()) {
        val now = queue.poll()
        if (now == end) {
            println(time[now])
            break
        }
        for (i in 0..2) {
            val next = when (i) {
                0 -> now - 1
                1 -> now + 1
                else -> now * 2
            }

            if (next in 0..100000 && time[next] == 0) {
                time[next] = time[now] + 1
                visited[next] = now
                queue.add(next)
            }
        }
    }

    var result = end
    val ls = mutableListOf(end)
    while (true) {
        ls.add(visited[result])
        result = visited[result]
        if (result == start) break
    }

    println(ls.reversed().joinToString(" "))
}