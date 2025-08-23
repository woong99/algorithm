import java.util.*
import kotlin.math.max

val dx = arrayOf(1, -1, 0, 0, 0, 0)
val dy = arrayOf(0, 0, 1, -1, 0, 0)
val dz = arrayOf(0, 0, 0, 0, 1, -1)

fun main() {
    val (m, n, h) = readln().split(" ").map { it.toInt() }
    val graph = Array(h) { Array(n) { IntArray(m) } }

    repeat(h) { i ->
        repeat(n) { ii ->
            graph[i][ii] = readln().split(" ").map { it.toInt() }.toIntArray()
        }
    }

    val startX = mutableListOf<Int>()
    val startY = mutableListOf<Int>()
    val startZ = mutableListOf<Int>()
    for (x in graph.indices) {
        for (y in graph.first().indices) {
            for (z in graph.first().first().indices) {
                if (graph[x][y][z] == 1) {
                    startX.add(x)
                    startY.add(y)
                    startZ.add(z)
                }
            }
        }
    }

    val count = bfs(startX, startY, startZ, graph)

    if (graph.any { g -> g.any { gg -> gg.any { it == 0 } } }) {
        println(-1)
    } else {
        println(count)
    }
}

private fun bfs(
    startX: MutableList<Int>,
    startY: MutableList<Int>,
    startZ: MutableList<Int>,
    graph: Array<Array<IntArray>>
): Int {
    val visited = Array(graph.size) { Array(graph.first().size) { BooleanArray(graph.first().first().size) } }
    val queue: Queue<Tomato> = LinkedList()

    repeat(startX.size) { i ->
        visited[startX[i]][startY[i]][startZ[i]] = true
        queue.add(Tomato(startX[i], startY[i], startZ[i], 0))
    }

    var result = -1
    while (queue.isNotEmpty()) {
        val (x, y, z, count) = queue.poll()
        result = max(result, count)

        for (i in 0..5) {
            val nx = x + dx[i]
            val ny = y + dy[i]
            val nz = z + dz[i]

            if (nx in graph.indices &&
                ny in graph.first().indices &&
                nz in graph.first().first().indices &&
                graph[nx][ny][nz] == 0 &&
                !visited[nx][ny][nz]
            ) {
                visited[nx][ny][nz] = true
                queue.add(Tomato(nx, ny, nz, count + 1))
                graph[nx][ny][nz] = 1
            }
        }
    }

    return result
}

data class Tomato(
    val x: Int,
    val y: Int,
    val z: Int,
    val count: Int
)