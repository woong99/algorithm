fun main() {
    val n = readln().toInt()
    val arr1 = readln().split(" ").map { it.toLong() }.sorted()

    val m = readln().toInt()
    val arr2 = readln().split(" ").map { it.toLong() }

    arr2.forEach { goal ->
        println(binarySearch(n, goal, arr1))
    }
}

fun binarySearch(
    n: Int,
    goal: Long,
    arr: List<Long>
): Long {
    var start = 0
    var end = n - 1
    while (start <= end) {
        val mid = (start + end) / 2

        if (arr[mid] == goal) {
            return 1
        }

        if (arr[mid] > goal) {
            end = mid - 1
        } else if (arr[mid] < goal) {
            start = mid + 1
        }
    }

    return 0
}