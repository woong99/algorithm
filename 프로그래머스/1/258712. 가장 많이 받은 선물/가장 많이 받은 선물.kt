class Solution {
    fun solution(friends: Array<String>, gifts: Array<String>): Int {
        var answer: Int = 0
        
        // 선물 주고 받은 관계 계산
        val graph = Array(friends.size){IntArray(friends.size)}
        gifts.forEach {
            val (a, b) = it.split(" ")
            graph[friends.indexOf(a)][friends.indexOf(b)] += 1
        }
        
        // 선물 지수 계산
        val giftCount = IntArray(friends.size)
        for(i in friends.indices) {
            // 선물 준 개수 계산
            val giveCount = graph[i].sum()
            
            // 선물 받은 개수 계산
            var givenCount = 0
            for(j in friends.indices) {
                givenCount += graph[j][i]
            }
            giftCount[i] = giveCount - givenCount
        }
        
        // 다음 달 주고 받은 선물 계산
        val total = IntArray(friends.size)
        for(i in friends.indices) {
            for(j in friends.indices) {
                if(i == j) continue
                
                if(graph[i][j] > graph[j][i]) {
                    total[i] += 1
                } else if(graph[i][j] < graph[j][i]) {
                    total[j] += 1
                } else {
                    if(giftCount[i] > giftCount[j]) {
                        total[i] += 1
                    } else if(giftCount[i] < giftCount[j]) {
                        total[j] += 1
                    }
                }
            }
        }

        return total.maxOrNull()?.div(2) ?: 0
    }
}