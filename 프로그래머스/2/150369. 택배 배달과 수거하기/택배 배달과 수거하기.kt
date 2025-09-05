import kotlin.math.*
import java.util.*
    
class Solution {
    fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {
        var answer: Long = 0
        val deliveryStack = Stack<Int>()
        val pickupStack = Stack<Int>()
        
        for(i in 0 until n) {
            if(deliveries[i] > 0) deliveryStack.push(i)
            if(pickups[i] > 0) pickupStack.push(i)
        }
           
        while(deliveryStack.isNotEmpty() || pickupStack.isNotEmpty()) {
            var capacity = cap
            val deliveryLastIndex = if(deliveryStack.isNotEmpty()) deliveryStack.peek() else -1
            val pickupLastIndex = if(pickupStack.isNotEmpty()) pickupStack.peek() else -1
            
            while(deliveryStack.isNotEmpty() && capacity != 0) {
                val idx = deliveryStack.peek()
                if(capacity >= deliveries[idx]) {
                    capacity -= deliveries[idx]
                    deliveries[idx] = 0
                    deliveryStack.pop()
                } else {
                    deliveries[idx] -= capacity
                    capacity = 0
                }
            }
            
            capacity = cap
            while(pickupStack.isNotEmpty() && capacity != 0) {
                val idx = pickupStack.peek()
                if(capacity >= pickups[idx]) {
                    capacity -= pickups[idx]
                    pickups[idx] = 0
                    pickupStack.pop()
                } else {
                    pickups[idx] -= capacity
                    capacity = 0
                }
            }
            
            
            answer += (max(deliveryLastIndex, pickupLastIndex) + 1) * 2
        }
        return answer
    }
}