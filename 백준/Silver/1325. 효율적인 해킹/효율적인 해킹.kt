fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val graph = Array(n) { mutableListOf<Int>() }
    val results = IntArray(n)

    repeat(m) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        graph[b - 1].add(a - 1)
    }

    for (i in 0 until n) {
        val visited = BooleanArray(n)
        results[i] = (dfs(i, graph, visited))
    }

    val maxCount = results.max()
    val result = results.withIndex()
        .filter { it.value == maxCount }
        .map { it.index + 1 }
    println(result.joinToString(" "))
}

private fun dfs(
    num: Int,
    graph: Array<MutableList<Int>>,
    visited: BooleanArray
): Int {
    visited[num] = true

    var count = 1
    for (i in graph[num]) {
        if (!visited[i]) {
            count += dfs(i, graph, visited)
        }
    }

    return count
}