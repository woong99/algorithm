lateinit var graph: Array<IntArray>

fun main() {
    val t = readln().toInt()

    for (i in 0..<t) {
        val (m, n, k) = readln().split(" ").map { it.toInt() }

        graph = Array(n) { IntArray(m) }

        for (j in 0..<k) {
            val (x, y) = readln().split(" ").map { it.toInt() }
            graph[y][x] = 1
        }

        var count = 0

        for (j in 0..<m) {
            for (h in 0..<n) {
                if (graph[h][j] == 1) {
                    count++
                    dfs(h, j, m, n)
                }
            }
        }
        println(count)
    }
}

val dx = intArrayOf(0, 0, 1, -1)
val dy = intArrayOf(1, -1, 0, 0)

private fun dfs(
    x: Int,
    y: Int,
    m: Int,
    n: Int
) {
    graph[x][y] = 2

    for (i in 0..3) {
        val dxx = x + dx[i]
        val dyy = y + dy[i]

        if (dxx >= 0 && dyy >= 0 && dxx < n && dyy < m && graph[dxx][dyy] == 1) {
            dfs(dxx, dyy, m, n)
        }
    }
}
