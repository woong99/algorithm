var visited: BooleanArray? = null

fun main() {

    val n = readln().toInt()
    val m = readln().toInt()

    val arr = Array(n) { IntArray(n) }
    visited = BooleanArray(n)

    for (i in 0..<m) {
        val input = readln().split(" ")
        val x = input[0].toInt()
        val y = input[1].toInt()
        arr[x - 1][y - 1] = 1
        arr[y - 1][x - 1] = 1
    }
    
    dfs(0, n, arr)

    println(visited?.count {
        it
    }?.minus(1) ?: 0)
}

private fun dfs(num: Int, n: Int, graph: Array<IntArray>) {
    visited?.set(num, true)

    for (i in (0..<n)) {
        if (graph[num][i] == 1 && visited?.get(i) == false) {
            dfs(i, n, graph)
        }
    }
}
