fun main() {
    val n = readln().toInt()
    val arr1 = readln().split(" ").map { it.toInt() }.sorted()
    val map = mutableMapOf<Int, Int>()
    arr1.forEach {
        if (map.containsKey(it)) {
            map[it] = map[it]!! + 1
        } else {
            map[it] = 1
        }
    }

    val m = readln().toLong()
    val arr2 = readln().split(" ").map { it.toInt() }

    val sb = StringBuilder()
    arr2.forEach {
        if (binarySearch(n, it, arr1) == 1) {
            sb.append("${map[it]} ")
        } else {
            sb.append("0 ")
        }
    }

    println(sb)
}

private fun binarySearch(
    n: Int,
    goal: Int,
    arr: List<Int>
): Int {
    var left = 0
    var right = n - 1

    while (left <= right) {
        val mid = (left + right) / 2

        if (goal == arr[mid]) {
            return 1
        }

        if (arr[mid] > goal) {
            right = mid - 1
        } else {
            left = mid + 1
        }
    }
    return 0
}