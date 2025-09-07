fun main() {
    val n = readln().toInt()
    val m = readln().toInt()
    val arr = readln().split(" ").map { it.toInt() }.sorted()

    var left = 0
    var right = n - 1
    var count = 0
    while (left < right) {
        val sum = arr[left] + arr[right]
        if (sum > m) {
            right--
        } else if (sum == m) {
            left++
            right--
            count++
        } else {
            left++
        }
    }

    println(count)
}