val dx = arrayOf(1, -1, 0, 0)
val dy = arrayOf(0, 0, 1, -1)

var sheepCount = 0
var wolfCount = 0

fun main() {
    val (r, c) = readln().split(" ").map { it.toInt() }
    val graph = Array(r) { CharArray(c) }
    val visited = Array(r) { BooleanArray(c) }

    repeat(r) { i ->
        graph[i] = readln().toCharArray()
    }

    var totalWolfCount = 0
    var totalSheepCount = 0

    for (i in graph.indices) {
        for (j in graph.first().indices) {
            if (!visited[i][j] && graph[i][j] != '#') {
                wolfCount = 0
                sheepCount = 0
                dfs(i, j, graph, visited)

                if (sheepCount > wolfCount) {
                    totalSheepCount += sheepCount
                } else {
                    totalWolfCount += wolfCount
                }
            }
        }
    }

    println("$totalSheepCount $totalWolfCount")
}

private fun dfs(
    x: Int,
    y: Int,
    graph: Array<CharArray>,
    visited: Array<BooleanArray>
) {
    visited[x][y] = true

    if (graph[x][y] == 'v') {
        wolfCount++
    } else if (graph[x][y] == 'o') {
        sheepCount++
    }

    for (i in 0..3) {
        val nx = x + dx[i]
        val ny = y + dy[i]

        if (nx in graph.indices && ny in graph.first().indices && graph[nx][ny] != '#' && !visited[nx][ny]) {
            dfs(nx, ny, graph, visited)
        }
    }
}