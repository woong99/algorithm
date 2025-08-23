import java.util.*
import kotlin.math.max

val dx = arrayOf(1, -1, 0, 0)
val dy = arrayOf(0, 0, 1, -1)

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val graph = Array(m) { IntArray(n) }
    repeat(m) { i ->
        graph[i] = readln().split(" ").map { it.toInt() }.toIntArray()
    }

    val startX = mutableListOf<Int>()
    val startY = mutableListOf<Int>()
    for (x in graph.indices) {
        for (y in graph.first().indices) {
            if (graph[x][y] == 1) {
                startX.add(x)
                startY.add(y)
            }
        }
    }

    val count = bfs(startX, startY, graph)

    if (graph.any { g -> g.any { it == 0 } }) {
        println(-1)
    } else {
        println(count)
    }
}

private fun bfs(
    startX: MutableList<Int>,
    startY: MutableList<Int>,
    graph: Array<IntArray>,
): Int {
    val visited = Array(graph.size) { BooleanArray(graph.first().size) }

    val queue: Queue<Triple<Int, Int, Int>> = LinkedList()
    startX.forEachIndexed { i, v ->
        visited[startX[i]][startY[i]] = true
        queue.add(Triple(startX[i], startY[i], 0))
    }

    var result = -1
    while (queue.isNotEmpty()) {
        val (nextX, nextY, count) = queue.poll()
        result = max(result, count)

        for (i in 0..3) {
            val nx = nextX + dx[i]
            val ny = nextY + dy[i]

            if (nx in graph.indices && ny in graph.first().indices && !visited[nx][ny] && graph[nx][ny] == 0) {
                queue.add(Triple(nx, ny, count + 1))
                visited[nx][ny] = true
                graph[nx][ny] = 1
            }
        }
    }

    return result
}