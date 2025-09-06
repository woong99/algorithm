import kotlin.math.abs
import kotlin.math.min

fun main() {
    readln().toInt()
    val arr = readln().split(" ").map { it.toInt() }.sorted()

    var minSum = Int.MAX_VALUE
    val ls = IntArray(2)
    var right = arr.size
    for (left in arr.indices) {
        var prev = Int.MAX_VALUE
        for (j in right - 1 downTo left + 1) {
            val temp = arr[left] + arr[j]

            if (abs(temp) < minSum) {
                minSum = min(abs(temp), minSum)

                if (arr[left] > arr[j]) {
                    ls[0] = arr[j]
                    ls[1] = arr[left]
                } else {
                    ls[0] = arr[left]
                    ls[1] = arr[j]
                }
                if (temp >= 0)
                    right = j
            } else {
                if (temp >= 0)
                    right = j
            }

            if (abs(temp) > prev) {
                break
            }
            prev = temp
        }
    }

    println(ls.joinToString(" "))
}