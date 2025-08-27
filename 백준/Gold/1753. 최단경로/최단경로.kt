import java.util.*

fun main() {
    val (v, e) = readln().split(" ").map { it.toInt() }
    val k = readln().toInt()
    val graph = Array(v) { mutableListOf<Pair<Int, Int>>() }
    repeat(e) {
        val (u, v, w) = readln().split(" ").map { it.toInt() }
        graph[u - 1].add(v - 1 to w)
    }

    val queue: PriorityQueue<Pair<Int, Int>> = PriorityQueue(compareBy { it.first })
    queue.add(0 to k - 1)

    val distance = IntArray(v) { Int.MAX_VALUE }
    distance[k - 1] = 0

    while (queue.isNotEmpty()) {
        val (weight, node) = queue.poll()

        for ((next, cost) in graph[node]) {
            if (distance[next] > cost + weight) {
                distance[next] = cost + weight
                queue.add(distance[next] to next)
            }
        }
    }

    distance.forEach {
        if (it == Int.MAX_VALUE) {
            println("INF")
        } else {
            println(it)
        }
    }
}

