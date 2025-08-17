import java.util.*

val dx = arrayOf(1, -1, 0, 0)
val dy = arrayOf(0, 0, 1, -1)
var result = -1

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val graph = Array(n) { IntArray(m) }


    repeat(n) { i ->
        graph[i] = readln().map { it.digitToInt() }.toIntArray()
    }

    bfs(graph)
    println(result)
}

data class BFS(
    val x: Int,
    val y: Int,
    val count: Int,
    val isBreak: Int,
)

private fun bfs(
    graph: Array<IntArray>,
) {
    val visited = Array(graph.size) { Array(graph.first().size) { BooleanArray(2) } }
    visited[0][0][0] = true

    val queue: Queue<BFS> = LinkedList()
    queue.add(BFS(0, 0, 1, 0))

    while (queue.isNotEmpty()) {
        val value = queue.poll()

        if (value.x == graph.size - 1 && value.y == graph.first().size - 1) {
            result = value.count
            break
        }

        for (i in 0..3) {
            val nx = value.x + dx[i]
            val ny = value.y + dy[i]

            // 범위 밖이면 Continue
            if (nx !in graph.indices || ny !in graph.first().indices) continue

            // 이미 한 번 벽을 깬 상태에서 다음 칸을 방문하지 않은 경우
            if (graph[nx][ny] == 0 && !visited[nx][ny][value.isBreak]) {
                queue.add(BFS(nx, ny, value.count + 1, value.isBreak))
                visited[nx][ny][value.isBreak] = true
            } else if (graph[nx][ny] == 1 && value.isBreak == 0 && !visited[nx][ny][1]) {
                queue.add(BFS(nx, ny, value.count + 1, 1))
                visited[nx][ny][1] = true
            }
        }
    }
}