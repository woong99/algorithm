import java.util.*

fun main() {
    val t = readln().toInt()
    repeat(t) {
        val (start, end) = readln().split(" ").map { it.toInt() }
        bfs(start, end)
    }
}

private fun bfs(
    start: Int,
    end: Int
) {
    val visited = BooleanArray(10001)
    visited[start] = true

    val queue: Queue<Pair<Int, String>> = LinkedList()
    queue.add(start to "")

    while (queue.isNotEmpty()) {
        val (next, command) = queue.poll()
        if (next == end) {
            println(command)
            break
        }

        // D
        val dNext = next * 2
        val dValue = if (dNext > 9999) {
            dNext % 10000
        } else {
            dNext
        }
        if (!visited[dValue]) {
            visited[dValue] = true
            queue.add(dValue to command + 'D')
        }

        // S
        val sValue = if (next == 0) {
            9999
        } else {
            next - 1
        }
        if (!visited[sValue]) {
            visited[sValue] = true
            queue.add(sValue to command + 'S')
        }

        // L
        val nextString = next.toString().padStart(4, '0')
        val lNext = nextString.substring(1, nextString.length) + nextString.first()
        val lNextInt = lNext.toInt()
        if (!visited[lNextInt]) {
            visited[lNextInt] = true
            queue.add(lNextInt to command + 'L')
        }

        // R
        val rNext = nextString.last() + nextString.substring(0, nextString.length - 1)
        val rNExtInt = rNext.toInt()
        if (!visited[rNExtInt]) {
            visited[rNExtInt] = true
            queue.add(rNExtInt to command + 'R')
        }
    }
}