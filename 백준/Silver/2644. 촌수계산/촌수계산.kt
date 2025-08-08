var result: Int = -1

fun main() {
    val n = readln().toInt()
    val graph = Array(n) { BooleanArray(n) }
    val visited = BooleanArray(n)

    val (a, b) = readln().split(" ").map { it.toInt() }

    val m = readln().toInt()
    repeat(m) {
        val (x, y) = readln().split(" ").map { it.toInt() }
        graph[x - 1][y - 1] = true
        graph[y - 1][x - 1] = true
    }

    val sum = 1
    for (i in graph.indices) {
        if (graph[a - 1][i]) {
            dfs(i, b, graph, visited, sum)
        }
    }

    println(result)
}

private fun dfs(
    num: Int,
    b: Int,
    graph: Array<BooleanArray>,
    visited: BooleanArray,
    sum: Int,
): Boolean {
    visited[num] = true
    if (b == num + 1) {
        result = sum
    }


    for (i in graph.indices) {
        if (graph[num][i] && !visited[i]) {
            dfs(i, b, graph, visited, sum + 1)
        }
    }

    return false
}