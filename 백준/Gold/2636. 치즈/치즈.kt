import java.util.*

val dx = arrayOf(1, -1, 0, 0)
val dy = arrayOf(0, 0, 1, -1)

fun main() {
    val (m, n) = readln().split(" ").map { it.toInt() }
    var graph = Array(m) { IntArray(n) }

    repeat(m) { i ->
        graph[i] = readln().split(" ").map { it.toInt() }.toIntArray()
    }

    var resultCount = 0
    var beforeCount = 0
    while (graph.any { g -> g.any { it == 1 } }) {
        beforeCount = graph.sumOf { g -> g.count { it == 1 } }

        val visited = Array(graph.size) { BooleanArray(graph.first().size) }
        resultCount++

        bfs(0, 0, graph, visited)

        val newGraph = Array(graph.size) { i -> graph[i].copyOf() }
        for (i in graph.indices) {
            for (j in graph.first().indices) {
                if (graph[i][j] == 1) {
                    var count = 0
                    for (k in 0..3) {
                        val nx = i + dx[k]
                        val ny = j + dy[k]

                        if (nx in graph.indices && ny in graph.first().indices && visited[nx][ny]) {
                            count++
                            break
                        }
                    }

                    if (count > 0) {
                        newGraph[i][j] = 0
                    }
                }
            }
        }

        graph = newGraph
    }

    println(resultCount)
    println(beforeCount)
}

private fun bfs(
    x: Int,
    y: Int,
    graph: Array<IntArray>,
    visited: Array<BooleanArray>
) {
    visited[x][y] = true

    val queue: Queue<Pair<Int, Int>> = LinkedList()
    queue.add(x to y)

    while (queue.isNotEmpty()) {
        val (nextX, nextY) = queue.poll()

        for (i in 0..3) {
            val nx = nextX + dx[i]
            val ny = nextY + dy[i]

            if (nx in graph.indices && ny in graph.first().indices && graph[nx][ny] == 0 && !visited[nx][ny]) {
                visited[nx][ny] = true
                queue.add(nx to ny)
            }
        }
    }
}