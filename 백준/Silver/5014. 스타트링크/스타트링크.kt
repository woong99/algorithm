import java.util.*
import kotlin.math.max

fun main() {
    val (f, s, g, u, d) = readln().split(" ").map { it.toInt() }

    bfs(s, g, f, u, d)
}

private fun bfs(
    start: Int,
    end: Int,
    size: Int,
    up: Int,
    down: Int
) {
    val visited = BooleanArray(size)
    visited[start - 1] = true

    val queue: Queue<Pair<Int, Int>> = LinkedList()
    queue.add(start - 1 to 0)

    var totalCount = 0
    var isPossible = false
    while (queue.isNotEmpty()) {
        val (next, count) = queue.poll()
        totalCount = max(totalCount, count)
        if (next == end - 1) {
            isPossible = true
            break
        }

        val upStair = next + up
        if (upStair < size && !visited[upStair]) {
            queue.add(upStair to count + 1)
            visited[upStair] = true
        }

        val downStair = next - down
        if (downStair >= 0 && !visited[downStair]) {
            queue.add(downStair to count + 1)
            visited[downStair] = true
        }
    }

    if (isPossible) {
        println(totalCount)
    } else {
        println("use the stairs")
    }
}