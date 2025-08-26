import java.util.*

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    bfs(n, k)
}

private fun bfs(
    start: Int,
    end: Int
) {
    val time = IntArray(100001)
    time[start] = 1

    val queue: Queue<Pair<Int, Int>> = LinkedList()
    queue.add(start to 0)

    val results = mutableListOf<Int>()
    while (queue.isNotEmpty()) {
        val (now, count) = queue.poll()
        if (now == end) {
            results.add(count)
        }

        // X - 1
        val minus = now - 1
        if (minus >= 0 && (time[minus] == 0 || time[minus] == time[now] + 1)) {
            queue.add(minus to count + 1)
            time[minus] = time[now] + 1
        }

        // X + 1
        val plus = now + 1
        if (plus <= 100000 && (time[plus] == 0 || time[plus] == time[now] + 1)) {
            queue.add(plus to count + 1)
            time[plus] = time[now] + 1
        }

        // 2 * X
        val multi = now * 2
        if (multi <= 100000 && (time[multi] == 0 || time[multi] == time[now] + 1)) {
            queue.add(multi to count + 1)
            time[multi] = time[now] + 1
        }
    }

    val min = results.min()
    println(min)
    println(results.count { it == min })
}