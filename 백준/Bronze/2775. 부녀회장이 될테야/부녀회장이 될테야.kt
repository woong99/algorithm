fun main() {
    val t = readln().toInt()
    for (i in 0..<t) {
        val k = readln().toInt()
        val n = readln().toInt()

        val dp = Array(15) { IntArray(16) }
        for (h in 1..15) {
            dp[0][h] = h
        }

        for (h in 1..k) {
            for (j in 1..n) {
                dp[h][j] = dp[h][j - 1] + dp[h - 1][j]
            }
        }

        println(dp[k][n])
    }
}
