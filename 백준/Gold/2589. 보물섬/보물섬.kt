import java.util.*
import kotlin.math.max

val dx = arrayOf(1, -1, 0, 0)
val dy = arrayOf(0, 0, 1, -1)

fun main() {
    val (l, w) = readln().split(" ").map { it.toInt() }
    val graph = Array(l) { readln().toCharArray() }

    var result = -1
    for (x in graph.indices) {
        for (y in graph.first().indices) {
            if (graph[x][y] == 'L') {
                val count = bfs(x, y, graph)
                result = max(result, count)
            }
        }
    }

    println(result)
}

private fun bfs(
    startX: Int,
    startY: Int,
    graph: Array<CharArray>
): Int {
    val visited = Array(graph.size) { BooleanArray(graph.first().size) }
    visited[startX][startY] = true

    val queue: Queue<Triple<Int, Int, Int>> = LinkedList()
    queue.add(Triple(startX, startY, 0))

    var result = -1
    while (queue.isNotEmpty()) {
        val (x, y, count) = queue.poll()
        result = max(result, count)

        for (i in 0..3) {
            val nx = x + dx[i]
            val ny = y + dy[i]

            if (nx in graph.indices && ny in graph.first().indices && graph[nx][ny] == 'L' && !visited[nx][ny]) {
                visited[nx][ny] = true
                queue.add(Triple(nx, ny, count + 1))
            }
        }
    }

    return result
}