import java.util.*

val dx = arrayOf(1, -1, 0, 0)
val dy = arrayOf(0, 0, 1, -1)

fun main() {
    var index = 1
    while (true) {
        val n = readln().toInt()
        if (n == 0) {
            break
        }

        val graph = Array(n) { IntArray(n) }

        repeat(n) { i ->
            graph[i] = readln().split(" ").map { it.toInt() }.toIntArray()
        }


        val distance = Array(n) { IntArray(n) { Int.MAX_VALUE } }
        distance[0][0] = graph[0][0]

        val queue = PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.third })
        queue.add(Triple(0, 0, graph[0][0]))

        while (queue.isNotEmpty()) {
            val (x, y, weight) = queue.poll()
            if (x == graph.size - 1 && y == graph.size - 1) {
                println("Problem $index: $weight")
            }

            for (i in 0..3) {
                val nx = x + dx[i]
                val ny = y + dy[i]

                if (nx in graph.indices && ny in graph.indices && distance[nx][ny] > weight + graph[nx][ny]) {
                    distance[nx][ny] = weight + graph[nx][ny]
                    queue.add(Triple(nx, ny, distance[nx][ny]))
                }
            }
        }

        index++
    }
}