fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val arr = readln().split(" ").map { it.toInt() }

    var count = 0
    var left = 0
    var sum = 0
    for (right in arr.indices) {
        sum += arr[right]
        while (sum > m && right > left) {
            sum -= arr[left]
            left++
        }
        
        if (sum == m) count++
    }

    println(count)
}