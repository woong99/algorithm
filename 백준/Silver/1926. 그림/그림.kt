lateinit var graph: Array<IntArray>
lateinit var visited: Array<BooleanArray>

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }

    graph = Array(n) { IntArray(m) }
    visited = Array(n) { BooleanArray(m) }

    for (i in 0..<n) {
        val input = readln().split(" ").map { it.toInt() }
        for (j in 0..<m) {
            graph[i][j] = input[j]
        }
    }

    val result = ArrayList<Int>()

    for (i in 0..<n) {
        for (j in 0..<m) {
            if (!visited[i][j] && graph[i][j] == 1) {
                result.add(dfs(j, i, n, m))
            }
        }
    }

    println(result.size)
    println(result.maxOrNull() ?: 0)
}

val dx = intArrayOf(0, 0, 1, -1)
val dy = intArrayOf(1, -1, 0, 0)

private fun dfs(
    x: Int,
    y: Int,
    n: Int,
    m: Int
): Int {
    var count = 1
    visited[y][x] = true

    for (i in 0..3) {
        val dxx = x + dx[i]
        val dyy = y + dy[i]

        if (dxx < 0 || dyy < 0 || dxx >= m || dyy >= n) {
            continue
        }

        if (!visited[dyy][dxx] && graph[dyy][dxx] == 1) {
            count += dfs(dxx, dyy, n, m)
        }
    }

    return count
}
