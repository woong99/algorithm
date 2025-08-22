import java.util.*

fun main() {
    val s = readln().toInt()
    bfs(s)
}

private fun bfs(
    goal: Int
) {
    val queue: Queue<Triple<Int, Int, Int>> = LinkedList()
    queue.add(Triple(1, 0, 0))

    val set = mutableSetOf(1 to 0)

    while (queue.isNotEmpty()) {
        val (now, clipboard, count) = queue.poll()
        if (now == goal) {
            println(count)
            break
        }

        // 복사
        if (now to now !in set) {
            queue.add(Triple(now, now, count + 1))
            set.add(now to now)
        }

        // 붙여넣기
        if (clipboard != 0 && now + clipboard to clipboard !in set) {
            queue.add(Triple(now + clipboard, clipboard, count + 1))
            set.add(now + clipboard to clipboard)
        }

        // 삭제
        if (now != 0 && now - 1 to clipboard !in set) {
            queue.add(Triple(now - 1, clipboard, count + 1))
            set.add(now - 1 to clipboard)
        }
    }
}