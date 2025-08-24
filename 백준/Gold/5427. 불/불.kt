import java.util.*

val dx = arrayOf(1, -1, 0, 0)
val dy = arrayOf(0, 0, 1, -1)

fun main() {
    repeat(readln().toInt()) {
        val (w, h) = readln().split(" ").map { it.toInt() }
        val graph = Array(h) { readln().toCharArray() }

        var startX = -1
        var startY = -1
        val fireX = mutableListOf<Int>()
        val fireY = mutableListOf<Int>()
        for (x in graph.indices) {
            for (y in graph.first().indices) {
                if (graph[x][y] == '@') {
                    startX = x
                    startY = y
                } else if (graph[x][y] == '*') {
                    fireX.add(x)
                    fireY.add(y)
                }
            }
        }

        val result = bfs(startX, startY, fireX, fireY, graph)
        if (result == -1) {
            println("IMPOSSIBLE")
        } else {
            println(result)
        }
    }
}

private fun bfs(
    startX: Int,
    startY: Int,
    fireX: MutableList<Int>,
    fireY: MutableList<Int>,
    graph: Array<CharArray>
): Int {
    val visited = Array(graph.size) { BooleanArray(graph.first().size) }
    val queue: Queue<Triple<Int, Int, Int>> = LinkedList()

    repeat(fireX.size) { i ->
        queue.add(Triple(fireX[i], fireY[i], 0))
        visited[fireX[i]][fireY[i]] = true
    }
    queue.add(Triple(startX, startY, 0))
    visited[startX][startY] = true

    while (queue.isNotEmpty()) {
        val (x, y, count) = queue.poll()
        for (i in 0..3) {
            val nx = x + dx[i]
            val ny = y + dy[i]

            if ((nx !in graph.indices || ny !in graph.first().indices) && graph[x][y] == '@') {
                return count + 1
            }

            if (nx in graph.indices && ny in graph.first().indices && !visited[nx][ny] && graph[nx][ny] == '.') {
                if (graph[x][y] == '*') {
                    visited[nx][ny] = true
                    queue.add(Triple(nx, ny, count + 1))
                    graph[nx][ny] = '*'
                } else if (graph[x][y] == '@') {
                    visited[nx][ny] = true
                    queue.add(Triple(nx, ny, count + 1))
                    graph[nx][ny] = '@'
                }
            }
        }
    }

    return -1
}