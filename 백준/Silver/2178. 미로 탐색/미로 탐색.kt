import java.util.*

val dx = arrayOf(1, -1, 0, 0)
val dy = arrayOf(0, 0, 1, -1)

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val graph = Array(n) { IntArray(m) }
    val visited = Array(n) { BooleanArray(m) }

    repeat(n) { i ->
        graph[i] = readln().map { it.digitToInt() }.toIntArray()
    }

    bfs(graph, visited)
}

private fun bfs(
    graph: Array<IntArray>,
    visited: Array<BooleanArray>
) {
    val queue: Queue<Triple<Int, Int, Int>> = LinkedList()
    queue.add(Triple(0, 0, 1))
    visited[0][0] = true

    while (queue.isNotEmpty()) {
        val (x, y, count) = queue.poll()


        for (i in 0..3) {
            val nx = x + dx[i]
            val ny = y + dy[i]

            if (nx == graph.size - 1 && ny == graph.first().size - 1) {
                println(count + 1)
                return
            }

            if (nx in graph.indices && ny in graph.first().indices && graph[nx][ny] == 1 && !visited[nx][ny]) {
                visited[nx][ny] = true
                queue.add(Triple(nx, ny, count + 1))
            }
        }

    }
}