fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val arr1 = readln().split(" ").map { it.toInt() }
    val arr2 = readln().split(" ").map { it.toInt() }

    var first = 0
    var second = 0

    val sb = StringBuilder()

    while (first != n || second != m) {
        if (first == n) {
            sb.append("${arr2[second]} ")
            second++
            continue
        } else if (second == m) {
            sb.append("${arr1[first]} ")
            first++
            continue
        }
        val firstValue = arr1[first]
        val secondValue = arr2[second]


        if (firstValue > secondValue) {
            sb.append("$secondValue ")
            second++
        } else {
            sb.append("$firstValue ")
            first++
        }
    }

    println(sb)
}