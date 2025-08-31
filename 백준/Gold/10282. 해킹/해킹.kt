import java.util.*
import kotlin.math.max

fun main() {
    repeat(readln().toInt()) {
        val (n, d, c) = readln().split(" ").map { it.toInt() }

        val graph = Array(n) { mutableListOf<Pair<Int, Int>>() }
        repeat(d) {
            val (a, b, s) = readln().split(" ").map { it.toInt() }
            graph[b - 1].add(a - 1 to s)
        }

        val distance = IntArray(n) { Int.MAX_VALUE }
        distance[c - 1] = 0
        val queue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
        queue.add(c - 1 to 0)

        while (queue.isNotEmpty()) {
            val (node, weight) = queue.poll()

            for ((nextNode, nextWeight) in graph[node]) {
                if (distance[nextNode] > weight + nextWeight) {
                    distance[nextNode] = weight + nextWeight
                    queue.add(nextNode to distance[nextNode])
                }
            }
        }

        val count = distance.count { it != Int.MAX_VALUE }
        var maxWeight = -1
        distance.forEach {
            if (it != Int.MAX_VALUE) {
                maxWeight = max(maxWeight, it)
            }
        }
        println("$count $maxWeight")
    }
}