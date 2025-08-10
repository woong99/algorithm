val dx = arrayOf(1, -1, 0, 0)
val dy = arrayOf(0, 0, 1, -1)

fun main() {
    val (m, n) = readln().split(" ").map { it.toInt() }
    val graph = Array(m) { IntArray(n) }
    val dp = Array(m) { IntArray(n) { -1 } }

    for (i in 0 until m) {
        val input = readln().split(" ").map { it.toInt() }
        input.forEachIndexed { index, value ->
            graph[i][index] = value
        }
    }

    println(dfs(0, 0, graph, dp))
}

private fun dfs(
    x: Int,
    y: Int,
    graph: Array<IntArray>,
    dp: Array<IntArray>
): Int {
    if (x == graph.size - 1 && y == graph[0].size - 1) return 1
    if (dp[x][y] != -1) return dp[x][y]

    dp[x][y] = 0
    for (i in 0..3) {
        val nx = x + dx[i]
        val ny = y + dy[i]

        if (nx in graph.indices && ny in graph.first().indices && graph[x][y] > graph[nx][ny]) {
            dp[x][y] += dfs(nx, ny, graph, dp)
        }
    }

    return dp[x][y]
}