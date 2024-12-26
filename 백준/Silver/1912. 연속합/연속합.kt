import kotlin.math.max

fun main() {
    val n = readln().toInt()
    val sum = IntArray(n)

    val input = readln().split(" ").map { it.toInt() }
    sum[0] = input[0]
    for (i in 1..<n) {
        sum[i] = max(sum[i - 1] + input[i], input[i])
    }
    println(sum.max())
}