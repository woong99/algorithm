import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()

    val arr1 = LongArray(n)
    val arr2 = LongArray(n)
    val arr3 = LongArray(n)
    val arr4 = LongArray(n)

    repeat(n) { i ->
        val arr = br.readLine().split(" ").map { it.toLong() }
        arr1[i] = arr[0]
        arr2[i] = arr[1]
        arr3[i] = arr[2]
        arr4[i] = arr[3]
    }

    val sumArr1 = LongArray(n * n)
    val sumArr2 = LongArray(n * n)

    var idx = 0
    for (i in arr1.indices) {
        for (j in 0 until arr2.size) {
            sumArr1[idx] = arr1[i] + arr2[j]
            sumArr2[idx++] = arr3[i] + arr4[j]
        }
    }

    sumArr1.sort()
    sumArr2.sort()

    var left = 0
    var right = sumArr1.size - 1

    var count = 0L
    while (left < sumArr1.size && right >= 0) {
        val sum = sumArr1[left] + sumArr2[right]

        if (sum == 0L) {
            val lv = sumArr1[left]
            val rv = sumArr2[right]
            var leftCount = 0L
            var rightCount = 0L
            while (left < sumArr1.size && lv == sumArr1[left]) {
                left++
                leftCount++
            }

            while (right >= 0 && rv == sumArr2[right]) {
                right--
                rightCount++
            }
            count += leftCount * rightCount
        } else if (sum > 0L) {
            right--
        } else {
            left++
        }
    }

    println(count)
}