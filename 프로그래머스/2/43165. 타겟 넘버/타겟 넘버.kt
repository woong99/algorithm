import java.util.*
class Solution {
    fun solution(numbers: IntArray, target: Int): Int {
        var answer = 0
        
        val queue: Queue<Triple<Int, Int, Int>> = LinkedList()
        queue.add(Triple(numbers[0], numbers[0], 0))
        queue.add(Triple(numbers[0], -numbers[0], 0))

        
        while(queue.isNotEmpty()) {
            val (next, sum, index) = queue.poll()
            if(index == numbers.size - 1 && sum == target) {
                answer++
            }
                
            val nextIndex = index + 1
            
            if( nextIndex < numbers.size) {
                queue.add(Triple(numbers[nextIndex], sum + numbers[nextIndex], nextIndex))
            }
            
             if( nextIndex < numbers.size) {
                queue.add(Triple(numbers[nextIndex], sum - numbers[nextIndex], nextIndex))
            }
        }
        
        
        
        
        return answer
    }
}