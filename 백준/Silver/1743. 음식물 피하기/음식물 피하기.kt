import kotlin.math.max

val dx = arrayOf(1, -1, 0, 0)
val dy = arrayOf(0, 0, 1, -1)

fun main() {
    val (n, m, k) = readln().split(" ").map { it.toInt() }
    val graph = Array(n) { IntArray(m) }
    val visited = Array(n) { BooleanArray(m) }

    repeat(k) { i ->
        val (r, c) = readln().split(" ").map { it.toInt() }
        graph[r - 1][c - 1] = 1
    }

    var result = 1
    for (i in graph.indices) {
        for (j in graph.first().indices) {
            if (graph[i][j] == 1 && !visited[i][j]) {
                result = max(dfs(i, j, graph, visited), result)
            }
        }
    }

    println(result)
}

private fun dfs(
    x: Int,
    y: Int,
    graph: Array<IntArray>,
    visited: Array<BooleanArray>
): Int {
    visited[x][y] = true

    var count = 1
    for (i in 0..3) {
        val nx = x + dx[i]
        val ny = y + dy[i]

        if (nx in graph.indices && ny in graph.first().indices && graph[nx][ny] == 1 && !visited[nx][ny]) {
            count += dfs(nx, ny, graph, visited)
        }
    }

    return count
}