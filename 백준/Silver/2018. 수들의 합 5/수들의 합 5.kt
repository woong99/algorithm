fun main() {
    val n = readln().toInt()

    var sum = 0
    var left = 1
    var count = 0
    for (right in 1..n) {
        sum += right

        while (sum > n && right > left) {
            sum -= left
            left++
        }

        if (sum == n) {
            count++
        }
    }

    println(count)
}