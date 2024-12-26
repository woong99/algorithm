lateinit var graph: Array<IntArray>
lateinit var visited: Array<BooleanArray>

val dx = intArrayOf(0, 0, 1, -1)
val dy = intArrayOf(1, -1, 0, 0)

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }

    graph = Array(n) { IntArray(m) }
    visited = Array(n) { BooleanArray(m) }

    for (i in 0..<n) {
        val input = readln()
        for (j in 0..<m) {
            graph[i][j] = input[j].digitToInt()
        }
    }

    val map = HashMap<Int, Int>()

    var index = 2
    for (i in 0..<n) {
        for (j in 0..<m) {
            if (!visited[i][j] && graph[i][j] == 0) {
                map[index] = dfs(j, i, n, m, index++)
            }
        }
    }

    for (i in 0..<n) {
        for (j in 0..<m) {
            var count = 0
            val tempSet = HashSet<Int>()
            if (graph[i][j] == 1) {
                count = 1
                for (k in 0..3) {
                    val dxx = j + dx[k]
                    val dyy = i + dy[k]

                    if (dxx < 0 || dyy < 0 || dxx >= m || dyy >= n) {
                        continue
                    }

                    val value = graph[dyy][dxx]
                    if (value != 1) {
                        tempSet.add(value)
                    }
                }
            }
            tempSet.forEach {
                count += map[it]!!
            }
            print(count % 10)
        }

        if (i != n - 1) {
            println()
        }
    }
}

private fun dfs(
    x: Int,
    y: Int,
    n: Int,
    m: Int,
    index: Int,
): Int {
    var count = 1
    visited[y][x] = true
    graph[y][x] = index

    for (i in 0..3) {
        val dxx = x + dx[i]
        val dyy = y + dy[i]

        if (dxx < 0 || dyy < 0 || dxx >= m || dyy >= n) {
            continue
        }

        if (!visited[dyy][dxx] && graph[dyy][dxx] == 0) {
            count += dfs(dxx, dyy, n, m, index)
        }
    }

    return count
}
