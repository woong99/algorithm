import kotlin.math.abs
import kotlin.math.min

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val arr = mutableListOf<Int>()
    repeat(n) {
        arr.add(readln().toInt())
    }
    arr.sort()

//    var left = 0
//    var right = n - 1
//    var result = Int.MAX_VALUE
//    while (left <= right) {
//        println("$left - $right - $result")
//        val minus = abs(arr[right] - arr[left])
//        if (minus >= m) {
//            result = min(result, minus)
//            right--
//        } else {
//            left++
//        }
//    }

    var left = 0
    var right = 1
    var result = Int.MAX_VALUE
    while (left <= right) {
        if (left >= n || right >= n) break
        val minus = abs(arr[right] - arr[left])
        if (minus < m) {
            right++
        } else {
            result = min(result, minus)
            left++
        }
    }

    println(result)
}