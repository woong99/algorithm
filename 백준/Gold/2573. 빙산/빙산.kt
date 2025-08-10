import kotlin.math.max

val dx = arrayOf(1, -1, 0, 0)
val dy = arrayOf(0, 0, 1, -1)

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    var graph = Array(n) { IntArray(m) }

    for (i in 0 until n) {
        val input = readln().split(" ").map { it.toInt() }
        input.forEachIndexed { index, value ->
            graph[i][index] = value
        }
    }

    var result = 0
    var isAllZero = false
    while (true) {
        result++
        val visited = Array(n) { BooleanArray(m) }
        val afterGraph = Array(n) { graph[it].copyOf() }
        for (x in afterGraph.indices) {
            for (y in afterGraph.first().indices) {
                if (afterGraph[x][y] != 0) {
                    var count = 0
                    for (i in 0..3) {
                        val nx = x + dx[i]
                        val ny = y + dy[i]

                        if (nx in graph.indices && ny in graph.first().indices && graph[nx][ny] == 0) {
                            count++
                        }
                    }

                    afterGraph[x][y] = max(graph[x][y] - count, 0)
                }
            }
        }
        graph = Array(n) { afterGraph[it].copyOf() }

        var count = 0
        for (x in graph.indices) {
            for (y in graph.first().indices) {
                if (afterGraph[x][y] != 0 && !visited[x][y]) {
                    dfs(x, y, afterGraph, visited)

                    count++
                    if (count > 1) {
                        break
                    }
                }
            }
        }

        isAllZero = afterGraph.all { row -> row.all { it == 0 } }

        if (isAllZero) {
            break
        }


        if (count > 1) {
            break
        }
    }

    println(if (isAllZero) 0 else result)
}

private fun dfs(
    x: Int,
    y: Int,
    graph: Array<IntArray>,
    visited: Array<BooleanArray>
) {
    visited[x][y] = true

    for (i in 0..3) {
        val nx = x + dx[i]
        val ny = y + dy[i]

        if (nx in graph.indices && ny in graph.first().indices && graph[nx][ny] != 0 && !visited[nx][ny]) {
            dfs(nx, ny, graph, visited)
        }
    }
}