lateinit var graph: Array<IntArray>
lateinit var visited: Array<BooleanArray>

fun main() {
    val (n, m, k) = readln().split(" ").map { it.toInt() }

    graph = Array(n) { IntArray(m) }
    visited = Array(n) { BooleanArray(m) }

    for (i in 0..<k) {
        val (x1, y1, x2, y2) = readln().split(" ").map { it.toInt() }

        for (j in y1..<y2) {
            for (h in x1..<x2) {
                graph[j][h] = 1
            }
        }
    }

    val result = ArrayList<Int>()

    for (i in 0..<n) {
        for (j in 0..<m) {
            if (!visited[i][j] && graph[i][j] == 0) {
                result.add(dfs(j, i, n, m))
            }
        }
    }

    println(result.size)
    println(result.sorted().joinToString(" "))
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

        if (!visited[dyy][dxx] && graph[dyy][dxx] == 0) {
            count += dfs(dxx, dyy, n, m)
        }
    }

    return count
}
