import kotlin.math.max

fun main() {
    val (n, d, k, c) = readln().split(" ").map { it.toInt() }
    val arr = IntArray(n)
    repeat(n) { i ->
        arr[i] = readln().toInt()
    }

    var left = 1
    var right = k

    val sushi = IntArray(d)
    for (i in 0 until k) {
        sushi[arr[i] - 1] += 1
    }

    var maxCount = 0
    while (left <= n) {
        sushi[c - 1] += 1
        maxCount = max(maxCount, sushi.count { it != 0 })
        sushi[c - 1] -= 1

        sushi[arr[left - 1] - 1] -= 1

        if (right >= n) {
            sushi[arr[right % n] - 1] += 1
        } else {
            sushi[arr[right] - 1] += 1
        }

        right++
        left++
    }

    println(maxCount)
}