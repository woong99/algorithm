fun main() {
    val n = readln().toInt()

    val graph = Array(n) { IntArray(n) }
    val visited = Array(n) { BooleanArray(n) }

    for (i in 0..<n) {
        val input = readln().map { it.toString().toInt() }.toIntArray()
        for (j in 0..<n) {
            graph[i][j] = input[j]
        }
    }

    val result = ArrayList<Int>()

    for (i in 0..<n) {
        for (j in 0..<n) {
            if (graph[i][j] == 1 && !visited[i][j]) {
                val sum = 0
                result.add(dfs(n, j, i, graph, visited, sum))
            }
        }
    }

    result.sort()
    println(result.size)
    result.map {
        println(it)
    }
}

val dx = intArrayOf(1, 0, -1, 0)
val dy = intArrayOf(0, 1, 0, -1)

private fun dfs(
    n: Int,
    x: Int,
    y: Int,
    graph: Array<IntArray>,
    visited: Array<BooleanArray>,
    sum: Int
): Int {
    var count = 1
    visited[y][x] = true

    for (i in 0..3) {
        val dxx = x + dx[i]
        val dyy = y + dy[i]

        if (dxx >= 0 && dyy >= 0 && dxx < n && dyy < n && !visited[dyy][dxx] && graph[dyy][dxx] == 1) {
            count += dfs(n, dxx, dyy, graph, visited, sum)
        }
    }

    return count
}
