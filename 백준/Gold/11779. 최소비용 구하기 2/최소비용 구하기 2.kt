import java.util.*
import kotlin.math.min

fun main() {
    val n = readln().toInt()
    val m = readln().toInt()

    val graph = Array(n) { IntArray(n) { -1 } }
    repeat(m) {
        val (a, b, c) = readln().split(" ").map { it.toInt() }
        if (graph[a - 1][b - 1] != -1) {
            graph[a - 1][b - 1] = min(graph[a - 1][b - 1], c)
        } else {
            graph[a - 1][b - 1] = c
        }
    }

    val (s, e) = readln().split(" ").map { it.toInt() }

    val distance = IntArray(n) { Int.MAX_VALUE }
    distance[s - 1] = 0

    val queue: PriorityQueue<Pair<Int, Int>> = PriorityQueue(compareBy { it.second })
    queue.add(s - 1 to 0)

    val visited = IntArray(n) { -1 }

    while (queue.isNotEmpty()) {
        val (node, weight) = queue.poll()
        if (node == e - 1) {
            println(weight)
            break
        }

        for (i in graph.indices) {
            if (graph[node][i] >= 0 && distance[i] > weight + graph[node][i]) {
                distance[i] = weight + graph[node][i]
                queue.add(i to distance[i])
                visited[i] = node
            }
        }
    }

    val ls = mutableListOf(e)
    var num = visited[e - 1]
    while (num != -1) {
        ls.add(num + 1)

        num = visited[num]
    }

    println(ls.size)
    println(ls.reversed().joinToString(" "))
}