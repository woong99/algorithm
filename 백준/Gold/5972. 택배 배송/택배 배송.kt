import java.util.*
import kotlin.math.min

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val graph = Array(n) { mutableListOf<Pair<Int, Int>>() }

    repeat(m) {
        val (a, b, c) = readln().split(" ").map { it.toInt() }

        val pair1 = graph[a - 1].find { it.first == b - 1 }
        val pair2 = graph[b - 1].find { it.first == a - 1 }
        if (pair1 != null && pair2 != null) {
            val weight = min(pair1.second, c)
            graph[a - 1].remove(pair1)
            graph[b - 1].remove(pair2)
            graph[a - 1].add(b - 1 to weight)
            graph[b - 1].add(a - 1 to weight)
        } else {
            graph[a - 1].add(b - 1 to c)
            graph[b - 1].add(a - 1 to c)
        }
    }

    val distance = IntArray(n) { Int.MAX_VALUE }
    distance[0] = 0

    val queue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
    queue.add(0 to 0)

    while (queue.isNotEmpty()) {
        val (node, weight) = queue.poll()
        if (node == n - 1) {
            println(weight)
            break
        }

        for ((iNode, iWeight) in graph[node]) {
            if (distance[iNode] > iWeight + weight) {
                distance[iNode] = iWeight + weight
                queue.add(iNode to distance[iNode])
            }
        }
    }
}