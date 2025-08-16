var count = 0
var isFinish = false

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val graph = Array(n) { IntArray(n) }

    repeat(n - 1) { i ->
        val (a, b, c) = readln().split(" ").map { it.toInt() }
        graph[a - 1][b - 1] = c
        graph[b - 1][a - 1] = c
    }

    repeat(m) {
        count = 0
        isFinish = false
        val visited = BooleanArray(n)
        val (a, b) = readln().split(" ").map { it.toInt() }
        dfs(a - 1, graph, visited, b - 1)
        println(count)
    }
}

private fun dfs(
    num: Int,
    graph: Array<IntArray>,
    visited: BooleanArray,
    goal: Int,
): Int {
    visited[num] = true

    for (n in graph.indices) {
        if (graph[num][n] != 0 && !visited[n]) {
            if (!isFinish) {
                count += graph[num][n]

            }
//            println("num : $num, n : $n, count : $count, finish : $isFinish")
            if (n == goal) {
                isFinish = true
                return 0
            }

            if (dfs(n, graph, visited, goal) == -1 && !isFinish) {
//                println("!!")
                count -= graph[num][n]
            }
        }
    }

    return -1
}

//5 1
//2 1 2
//1 3 3
//4 2 4
//5 3 6
//2 3