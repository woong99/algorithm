import java.util.*

val dx = arrayOf(1, 2, 2, 1, -1, -2, -2, -1)
val dy = arrayOf(-2, -1, 1, 2, 2, 1, -1, -2)

fun main() {
    val n = readln().toInt()
    repeat(n) {
        val i = readln().toInt()
        val visited = Array(i) { BooleanArray(i) }

        val (startX, startY) = readln().split(" ").map { it.toInt() }
        val (endX, endY) = readln().split(" ").map { it.toInt() }
        bfs(startX, startY, endX, endY, visited, i)
    }
}

private fun bfs(
    startX: Int,
    startY: Int,
    endX: Int,
    endY: Int,
    visited: Array<BooleanArray>,
    length: Int
) {
    visited[startX][startY] = true
    val queue: Queue<Triple<Int, Int, Int>> = LinkedList()
    queue.add(Triple(startX, startY, 0))

    while (queue.isNotEmpty()) {
        val (x, y, count) = queue.poll()

        if (x == endX && y == endY) {
            println(count)
            break
        }

        for (i in 0..7) {
            val nx = x + dx[i]
            val ny = y + dy[i]

            if (nx in 0 until length && ny in 0 until length && !visited[nx][ny]) {
                queue.add(Triple(nx, ny, count + 1))
                visited[nx][ny] = true
            }
        }
    }
}