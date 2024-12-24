lateinit var graph: Array<IntArray>
lateinit var visited: BooleanArray
fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }

    graph = Array(n) { IntArray(n) }
    visited = BooleanArray(n)

    for (i in 0..<m) {
        val (x, y) = readln().split(" ").map { it.toInt() - 1 }

        graph[x][y] = 1
        graph[y][x] = 1
    }

    var count = 0
    for (i in 0..<n) {
        if (!visited[i]) {
            dfs(i, n)
            count++;
        }
    }

    println(count)
}

private fun dfs(
    num: Int,
    max: Int
) {
    visited[num] = true

    for (i in 0..<max) {
        if (graph[num][i] == 1 && !visited[i]) {
            dfs(i, max)
        }
    }
}

