import java.util.*
import kotlin.math.abs

val dx = arrayOf(1, -1, 0, 0)
val dy = arrayOf(0, 0, 1, -1)
var totalChanged = 0

fun main() {
    val (n, l, r) = readln().split(" ").map { it.toInt() }
    var graph = Array(n) { IntArray(n) }

    repeat(n) { i ->
        graph[i] = readln().split(" ").map { it.toInt() }.toIntArray()
    }


    var afterGraph = Array(graph.size) { it -> graph[it].copyOf() }
    var count = 0
    while (true) {
        val visited = Array(graph.size) { BooleanArray(graph.size) }
        totalChanged = 0
        for (x in 0 until n) {
            for (y in 0 until n) {
                if (!visited[x][y]) {
                    afterGraph = bfs(x, y, l, r, graph, afterGraph, visited)
                }
            }
        }
        if (totalChanged > 0) {
            count++
        }

        if (totalChanged == 0) {
            break
        }

        graph = Array(graph.size) { it -> afterGraph[it].copyOf() }
    }

    println(count)
}

private fun bfs(
    startX: Int,
    startY: Int,
    minRange: Int,
    maxRange: Int,
    graph: Array<IntArray>,
    afterGraph: Array<IntArray>,
    visited: Array<BooleanArray>
): Array<IntArray> {
    visited[startX][startY] = true

    val queue: Queue<Pair<Int, Int>> = LinkedList()
    queue.add(startX to startY)

    var totalPeople = graph[startX][startY]
    var count = 0
    val changedList = mutableListOf<Pair<Int, Int>>()
    while (queue.isNotEmpty()) {
        val (x, y) = queue.poll()
        changedList.add(x to y)
        count++

        for (i in 0..3) {
            val nx = x + dx[i]
            val ny = y + dy[i]

            if (
                nx in graph.indices &&
                ny in graph.indices &&
                abs(graph[nx][ny] - graph[x][y]) >= minRange &&
                abs(graph[nx][ny] - graph[x][y]) <= maxRange &&
                !visited[nx][ny]
            ) {
                visited[nx][ny] = true
                totalPeople += graph[nx][ny]
                queue.add(nx to ny)
            }
        }
    }

    val result = totalPeople / count

    if (count > 1) {
        changedList.forEach { (x, y) ->
            afterGraph[x][y] = result
            totalChanged++
        }
    }

    return afterGraph
}