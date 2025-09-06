import java.util.*

class Solution {
    fun solution(users: Array<IntArray>, emoticons: IntArray): IntArray {
        var answer: IntArray = intArrayOf(-1, -1)
        
        val possibles = mutableListOf<List<Int>>()
        dfs(0, emoticons.size, IntArray(emoticons.size), possibles)
        
        var totalSubscribePlus = 0
        var totalEmoticonPrice = 0
        
        possibles.forEach { discounts ->
            // 각 사용자마다 몇 개의 이모티콘을 살 지 혹은 플러스를 구독할 지 계산
            var subscribePlus = 0
            var emoticonPrice = 0
            for((percent, maxPrice) in users) {
                // 사용자 별로 얼마의 이모티콘을 구입하는지 계산
                var totalPrice = 0
                discounts.forEachIndexed {idx, discount ->
                    if(discount >= percent) {
                        totalPrice += emoticons[idx] -  (emoticons[idx] * discount / 100)
                    }
                }
                
                if(totalPrice >= maxPrice) { 
                    subscribePlus += 1 // 이모티콘 플러스 구독
                } else {
                    emoticonPrice += totalPrice // 이모티콘 구매
                }
            }
            
            if(answer[0] < subscribePlus) {
                answer[0] = subscribePlus
                answer[1] = emoticonPrice
            } else if(answer[0] == subscribePlus) {
                if(answer[1] < emoticonPrice) {
                    answer[1] = emoticonPrice
                }
            }
        }
        return answer
    }
    
    fun dfs(
        depth: Int,
        n: Int,
        arr: IntArray,
        possibles: MutableList<List<Int>>
    ) {
        if(depth == n) {
            possibles.add(arr.toList())
            return
        }
        
        for(i in 1..4) {
            arr[depth] = i * 10
            dfs(depth + 1, n, arr, possibles)
        }
    }
}