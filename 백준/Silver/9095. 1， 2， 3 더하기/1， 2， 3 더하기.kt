fun main() {
    val t = readln().toInt()

    val dp = IntArray(11)
    dp[1] = 1
    dp[2] = 2
    dp[3] = 4

    for (i in 0..<t) {
        val n = readln().toInt()

        for (j in 4..n) {
            dp[j] = dp[j - 1] + dp[j - 2] + dp[j - 3]
        }
        println(dp[n])
    }
}
