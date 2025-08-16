import java.util.*

val dx = arrayOf(1, -1, 0, 0)
val dy = arrayOf(0, 0, 1, -1)
val resultSet = mutableSetOf<String>()

fun main() {
    val graph = Array(5) { CharArray(5) }
    repeat(5) { i ->
        graph[i] = readln().split(" ").joinToString("").toCharArray()
    }

    for (i in 0..4) {
        for (j in 0..4) {
            val result = Stack<Char>()
            dfs(i, j, graph, 0, result)
        }
    }

    println(resultSet.count())
}

private fun dfs(
    x: Int,
    y: Int,
    graph: Array<CharArray>,
    depth: Int,
    result: Stack<Char>
) {
    if (depth == 6) {
        resultSet.add(result.joinToString(""))
        return
    } else {
        result.add(graph[x][y])
    }

    for (i in 0..3) {
        val nx = x + dx[i]
        val ny = y + dy[i]

        if (nx in 0..4 && ny in 0..4) {
            dfs(nx, ny, graph, depth + 1, result)

        }
    }

    result.pop()
}