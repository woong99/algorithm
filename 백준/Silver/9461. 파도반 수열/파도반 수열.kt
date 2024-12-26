fun main() {
    val t = readln().toInt()

    val dp = LongArray(102)

    for (i in 0..<t) {
        val n = readln().toInt()

        dp[1] = 1
        dp[2] = 1
        dp[3] = 1

        for (j in 4..n) {
            dp[j] = dp[j - 2] + dp[j - 3]
        }

        println(dp[n])
    }
}