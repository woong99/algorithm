fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val arr = readln().split(" ").map { it.toInt() }

    var count = 0
    for (i in arr.indices) {
        var pointer = i
        var sum = 0
        while (true) {
            sum += arr[pointer]
            pointer += 1

            if (sum == m) {
                count++
                break
            } else if (sum > m) {
                break
            }
            if (pointer >= arr.size) break
        }
    }

    println(count)
}