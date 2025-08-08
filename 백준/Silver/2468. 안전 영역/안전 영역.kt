import kotlin.math.max

val dx = arrayOf(1, -1, 0, 0)
val dy = arrayOf(0, 0, 1, -1)

fun main() {
    val n = readln().toInt()
    val graph = Array(n) { IntArray(n) }
    lateinit var visited: Array<BooleanArray>

    for (i in 0 until n) {
        val input = readln().split(" ").map { it.toInt() }
        input.forEachIndexed { index, value ->
            graph[i][index] = value
        }
    }

    val max = graph.maxOf { it.max() }
    var maxCount = 1

    for (k in 1..max) {
        var count = 0
        // 물에 잠기는 지역 체크
        val newGraph = graph.copyOf()
        for (i in graph.indices) {
            for (j in graph.indices) {
                if (newGraph[i][j] <= k) {
                    newGraph[i][j] = 0
                }
            }
        }

        visited = Array(n) { BooleanArray(n) }
        for (i in graph.indices) {
            for (j in graph.indices) {
                if (newGraph[i][j] != 0 && !visited[i][j]) {
                    dfs(i, j, newGraph, visited)
                    count++
                }
            }
        }

        maxCount = max(count, maxCount)
    }

    println(maxCount)
}

private fun dfs(
    x: Int,
    y: Int,
    graph: Array<IntArray>,
    visited: Array<BooleanArray>
) {
    visited[x][y] = true

    for (i in 0..3) {
        val dxx = x + dx[i]
        val dyy = y + dy[i]

        if (dxx in graph.indices && dyy in graph.indices && graph[dxx][dyy] != 0 && !visited[dxx][dyy]) {
            dfs(dxx, dyy, graph, visited)
        }
    }
}