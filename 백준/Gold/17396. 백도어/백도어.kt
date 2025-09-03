import java.util.*

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val graph = Array(n) { mutableListOf<Pair<Int, Int>>() }

    val visible = readln().split(" ").map { it.toInt() }.toIntArray()
    visible[visible.lastIndex] = 0
    repeat(m) {
        val (a, b, t) = readln().split(" ").map { it.toInt() }
        if (visible[a] != 1 && visible[b] != 1) {
            graph[a].add(b to t)
            graph[b].add(a to t)
        }
    }
    println(dijkstra(graph))
}

private fun dijkstra(
    graph: Array<MutableList<Pair<Int, Int>>>,
): Long {
    val distance = LongArray(graph.size) { Long.MAX_VALUE }
    distance[0] = 0
    val queue = PriorityQueue<Pair<Int, Long>>(compareBy { it.second })
    queue.add(0 to 0)

    while (queue.isNotEmpty()) {
        val (node, weight) = queue.poll()
        if (weight > distance[node]) continue
        if (node == graph.size - 1) return weight

        for ((n, w) in graph[node]) {
            if (distance[n] > w + weight) {
                distance[n] = w + weight
                queue.add(n to distance[n])
            }
        }
    }

    return -1
}
