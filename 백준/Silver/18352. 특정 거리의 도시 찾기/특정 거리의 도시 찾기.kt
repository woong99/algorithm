import java.util.*

fun main() {
    val (n, m, k, x) = readln().split(" ").map { it.toInt() }
    val graph = Array(n) { mutableListOf<Int>() }

    repeat(m) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        graph[a - 1].add(b - 1)
    }

    bfs(x - 1, k, graph)
}

private fun bfs(
    start: Int,
    goal: Int,
    graph: Array<MutableList<Int>>
) {
    val visited = BooleanArray(graph.size)
    visited[start] = true

    val queue: Queue<Pair<Int, Int>> = LinkedList()
    queue.add(start to 0)

    val result = mutableListOf<Int>()

    while (queue.isNotEmpty()) {
        val (next, count) = queue.poll()
        if (count == goal) {
            result.add(next + 1)
        }

        for (i in graph[next]) {
            if (!visited[i]) {
                queue.add(i to count + 1)
                visited[i] = true
            }
        }
    }

    if (result.isEmpty()) {
        println(-1)
    } else {
        result.sorted().forEach { println(it) }
    }
}