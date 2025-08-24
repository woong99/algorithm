import java.util.*
import kotlin.math.min

val dx = arrayOf(1, -1, 0, 0)
val dy = arrayOf(0, 0, 1, -1)

fun main() {
    val n = readln().toInt()
    val graph = Array(n) { readln().split(" ").map { it.toInt() }.toIntArray() }

    val visited = Array(graph.size) { BooleanArray(graph.size) }
    var mark = 2
    for (x in graph.indices) {
        for (y in graph.first().indices) {
            if (graph[x][y] == 1 && !visited[x][y]) {
                markBfs(x, y, graph, visited, mark++)
            }
        }
    }

    var count = Int.MAX_VALUE
    for (x in graph.indices) {
        for (y in graph.first().indices) {
            if (graph[x][y] != 0) {
                count = min(count, bfs(x, y, graph))
            }
        }
    }

    println(count)
}

private fun markBfs(
    startX: Int,
    startY: Int,
    graph: Array<IntArray>,
    visited: Array<BooleanArray>,
    mark: Int
) {
    visited[startX][startY] = true
    graph[startX][startY] = mark

    val queue: Queue<Pair<Int, Int>> = LinkedList()
    queue.add(startX to startY)

    while (queue.isNotEmpty()) {
        val (x, y) = queue.poll()

        for (i in 0..3) {
            val nx = x + dx[i]
            val ny = y + dy[i]

            if (nx in graph.indices && ny in graph.first().indices && !visited[nx][ny] && graph[nx][ny] == 1) {
                visited[nx][ny] = true
                queue.add(nx to ny)
                graph[nx][ny] = mark
            }
        }
    }
}

private fun bfs(
    startX: Int,
    startY: Int,
    graph: Array<IntArray>
): Int {
    val visited = Array(graph.size) { BooleanArray(graph.size) }
    visited[startX][startY] = true

    val queue: Queue<Bridge> = LinkedList()
    queue.add(Bridge(startX, startY, 0, graph[startX][startY]))

    var result = Int.MAX_VALUE
    while (queue.isNotEmpty()) {
        val (x, y, count, marker) = queue.poll()
        if (graph[x][y] != 0 && graph[x][y] != marker && count != 0) {
            result = min(result, count - 1)
        }

        for (i in 0..3) {
            val nx = x + dx[i]
            val ny = y + dy[i]

            if (nx in graph.indices && ny in graph.first().indices && !visited[nx][ny] && graph[nx][ny] != marker) {
                visited[nx][ny] = true
                queue.add(Bridge(nx, ny, count + 1, marker))
            }
        }
    }

    return result
}

data class Bridge(
    val x: Int,
    val y: Int,
    val count: Int,
    val marker: Int,
)