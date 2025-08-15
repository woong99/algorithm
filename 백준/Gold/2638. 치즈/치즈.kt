val dx = arrayOf(1, -1, 0, 0)
val dy = arrayOf(0, 0, 1, -1)

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    var graph = Array(n) { IntArray(m) }

    repeat(n) { index ->
        val input = readln().split(" ").map { it.toInt() }
        input.forEachIndexed { i, v ->
            graph[index][i] = v
        }
    }

    var resultCount = 0
    while (true) {
        resultCount++
        val visited = Array(n) { BooleanArray(m) }
        dfs(0, 0, graph, visited)

        val afterGraph = Array(graph.size) { i ->
            graph[i].clone()
        }

        for (x in 1 until n) {
            for (y in 1 until m) {
                var count = 0
                for (k in 0..3) {
                    val nx = x + dx[k]
                    val ny = y + dy[k]
                    if (nx in graph.indices && ny in graph.first().indices && visited[nx][ny]) {
                        count++
                    }
                }

                if (count >= 2) {
                    afterGraph[x][y] = 0
                }
            }
        }

        val result = afterGraph.any { ag -> ag.any { it == 1 } }
        if (!result) {
            println(resultCount)
            break
        }

        graph = afterGraph
    }
}

private fun dfs(
    x: Int,
    y: Int,
    graph: Array<IntArray>,
    visited: Array<BooleanArray>
) {
    visited[x][y] = true

    for (i in dx.indices) {
        val nx = x + dx[i]
        val ny = y + dy[i]

        if (nx in graph.indices && ny in graph.first().indices && graph[nx][ny] == 0 && !visited[nx][ny]) {
            dfs(nx, ny, graph, visited)
        }
    }
}