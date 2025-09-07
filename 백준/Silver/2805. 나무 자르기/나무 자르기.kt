import kotlin.math.max

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val arr = readln().split(" ").map { it.toInt() }

    var left = 0
    var right = arr.max()
    var maxHeight = 0
    while (left <= right) {
        val mid = (right + left) / 2

        val sum = arr.filter { it - mid > 0 }
            .sumOf { (it - mid).toLong() }

        if (sum >= m) {
            maxHeight = max(maxHeight, mid)
            left = mid + 1
        } else {
            right = mid - 1
        }
    }

    println(maxHeight)
}