import java.util.*

fun main() {
    val (n, m, r) = readln().split(" ").map { it.toInt() }
    val graph = Array(n) { mutableListOf<Int>() }
    repeat(m) {
        val (u, v) = readln().split(" ").map { it.toInt() }
        graph[u - 1].add(v - 1)
        graph[v - 1].add(u - 1)
    }

    graph.forEach { it.sort() }
    bfs(r - 1, graph)
}

private fun bfs(
    start: Int,
    graph: Array<MutableList<Int>>
) {
    val visited = IntArray(graph.size)
    visited[start] = 1

    val queue: Queue<Int> = LinkedList()
    queue.add(start)

    var count = 1
    while (queue.isNotEmpty()) {
        val next = queue.poll()

        for (i in graph[next]) {
            if (visited[i] == 0) {
                visited[i] = ++count
                queue.add(i)
            }
        }
    }

    visited.forEach { println(it) }
}