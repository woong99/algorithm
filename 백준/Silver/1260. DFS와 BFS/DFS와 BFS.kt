import java.util.*

val dfsResult = mutableListOf<Int>()
val bfsResult = mutableListOf<Int>()

fun main() {
    val (n, m, v) = readln().split(" ").map { it.toInt() }

    val graph = Array(n) { IntArray(n) }

    repeat(m) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        graph[a - 1][b - 1] = 1
        graph[b - 1][a - 1] = 1
    }

    var visited = BooleanArray(n)
    dfs(v - 1, graph, visited)

    visited = BooleanArray(n)
    bfs(v - 1, graph, visited)

    println(dfsResult.joinToString(" "))
    println(bfsResult.joinToString(" "))
}

private fun dfs(
    num: Int,
    graph: Array<IntArray>,
    visited: BooleanArray
) {
    visited[num] = true
    dfsResult.add(num + 1)

    for (i in graph.indices) {
        if (graph[num][i] == 1 && !visited[i]) {
            dfs(i, graph, visited)
        }
    }
}

private fun bfs(
    start: Int,
    graph: Array<IntArray>,
    visited: BooleanArray
) {
    val queue: Queue<Int> = LinkedList()
    queue.add(start)
    visited[start] = true

    while (queue.isNotEmpty()) {
        val num = queue.poll()
        bfsResult.add(num + 1)

        for (i in graph.indices) {
            if (graph[num][i] == 1 && !visited[i]) {
                queue.add(i)
                visited[i] = true
            }
        }
    }
}