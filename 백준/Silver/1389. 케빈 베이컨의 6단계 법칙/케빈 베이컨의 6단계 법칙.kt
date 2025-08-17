import java.util.*

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val graph = Array(n) { IntArray(n) }

    repeat(m) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        graph[a - 1][b - 1] = 1
        graph[b - 1][a - 1] = 1
    }

    val results = mutableListOf<Pair<Int, Int>>()
    for (i in 0 until n) {
        results.add(bfs(i, graph))
    }

    val minCount = results.minOf { it.second }
    println(results.find { it.second == minCount }!!.first + 1)
}

private fun bfs(
    start: Int,
    graph: Array<IntArray>
): Pair<Int, Int> {
    val visited = BooleanArray(graph.size)
    visited[start] = true

    val queue: Queue<Pair<Int, Int>> = LinkedList()
    queue.add(start to 0)

    var total = 0
    while (queue.isNotEmpty()) {
        val (num, count) = queue.poll()
        total += count

        for (i in graph[num].indices) {
            if (!visited[i] && graph[num][i] == 1) {
                visited[i] = true
                queue.add(i to count + 1)
            }
        }
    }

    return start to total
}