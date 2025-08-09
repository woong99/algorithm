import kotlin.math.max

val dx = arrayOf(1, -1, 0, 0)
val dy = arrayOf(0, 0, 1, -1)
var maxCount = 1

fun main() {
    val (r, c) = readln().split(" ").map { it.toInt() }

    val graph = Array(r) { CharArray(c) }
    val visited = Array(r) { BooleanArray(c) }
    val visitedAlpha = mutableSetOf<Char>()

    for (i in 0 until r) {
        val input = readln().toCharArray()
        input.forEachIndexed { index, value ->
            graph[i][index] = value
        }
    }

    dfs(0, 0, graph, visited, visitedAlpha, 1)
    println(maxCount)
}

private fun dfs(
    x: Int,
    y: Int,
    graph: Array<CharArray>,
    visited: Array<BooleanArray>,
    visitedAlpha: MutableSet<Char>,
    depth: Int
) {
    visited[x][y] = true
    visitedAlpha.add(graph[x][y])

    for (i in 0..3) {
        val dxx = x + dx[i]
        val dyy = y + dy[i]

        if (dxx in graph.indices && dyy in graph.first().indices && !visited[dxx][dyy] && graph[dxx][dyy] !in visitedAlpha) {
            dfs(dxx, dyy, graph, visited, visitedAlpha, depth + 1)
        }
    }

    maxCount = max(maxCount, depth)

    visited[x][y] = false
    visitedAlpha.remove(graph[x][y])
}