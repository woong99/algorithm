val dx = arrayOf(1, -1, 0, 0)
val dy = arrayOf(0, 0, 1, -1)
var count = 0

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val graph = Array(n) { CharArray(m) }
    val visited = Array(n) { BooleanArray(m) }

    repeat(n) { i ->
        graph[i] = readln().toCharArray()
    }

    var x = 0
    var y = 0
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (graph[i][j] == 'I') {
                x = i
                y = j
            }
        }
    }

    dfs(x, y, graph, visited)

    if (count == 0) println("TT") else println(count)
}

private fun dfs(
    x: Int,
    y: Int,
    graph: Array<CharArray>,
    visited: Array<BooleanArray>
) {
    visited[x][y] = true
    if (graph[x][y] == 'P') count++

    for (i in 0..3) {
        val nx = x + dx[i]
        val ny = y + dy[i]

        if (nx in graph.indices && ny in graph.first().indices && graph[nx][ny] != 'X' && !visited[nx][ny]) {
            dfs(nx, ny, graph, visited)
        }
    }
}