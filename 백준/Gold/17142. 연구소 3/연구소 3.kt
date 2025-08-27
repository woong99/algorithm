import java.util.*
import kotlin.math.min

val dx = arrayOf(1, -1, 0, 0)
val dy = arrayOf(0, 0, 1, -1)

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val graph = Array(n) { readln().split(" ").map { it.toInt() }.toIntArray() }

    val virus = mutableListOf<Pair<Int, Int>>()
    for (x in graph.indices) {
        for (y in graph.indices) {
            if (graph[x][y] == 2) {
                virus.add(x to y)
            }
        }
    }

    val possibleVirus = combination(virus, m)

    var minCount = Int.MAX_VALUE
    var isNotEnd = false
    possibleVirus.forEach { pv ->
        val copiedGraph = Array(graph.size) { graph[it].copyOf() }
        val result = bfs(copiedGraph, pv)
        if (result == -1) {
            isNotEnd = true
        } else {
            minCount = min(minCount, result)
        }
    }

    if (minCount == Int.MAX_VALUE && isNotEnd) {
        println(-1)
    } else {
        println(minCount)
    }
}

fun bfs(
    graph: Array<IntArray>,
    virus: List<Pair<Int, Int>>
): Int {
    val visited = Array(graph.size) { BooleanArray(graph.size) }
    val queue: Queue<Triple<Int, Int, Int>> = LinkedList()

    virus.forEach {
        visited[it.first][it.second] = true
        queue.add(Triple(it.first, it.second, 0))
        graph[it.first][it.second] = 3
    }

    while (queue.isNotEmpty()) {
        val (x, y, count) = queue.poll()

        if (!graph.any { g -> g.any { it == 0 } }) {
            if (queue.any { it.third > count }) {
                return count + 1
            }
            return count
        }

        for (i in 0..3) {
            val nx = x + dx[i]
            val ny = y + dy[i]

            if (nx in graph.indices && ny in graph.indices && !visited[nx][ny] && (graph[nx][ny] != 1)) {
                visited[nx][ny] = true
                queue.add(Triple(nx, ny, count + 1))
                graph[nx][ny] = 3
            }
        }
    }

    return -1
}

fun <T> combination(
    original: List<T>,
    n: Int
): List<List<T>> {
    val result = mutableListOf<List<T>>()

    fun dfs(
        start: Int,
        comb: MutableList<T>
    ) {
        if (comb.size == n) {
            result.add(comb.toList())
            return
        }

        for (i in start until original.size) {
            comb.add(original[i])
            dfs(i + 1, comb)
            comb.removeAt(comb.lastIndex)
        }
    }

    dfs(0, mutableListOf())

    return result.toList()
}