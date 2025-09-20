import java.util.*
import kotlin.math.*

class Solution {
    fun solution(dice: Array<IntArray>): IntArray {
        var answer: IntArray = intArrayOf()
        val total = 6.toDouble().pow(dice.size).toInt()
        
        val halfDiceSize = dice.size / 2
        
        // 주사위 고르는 경우의 수 구하기
        val items = (1..(dice.size)).toList()
        val comb = combination(items, halfDiceSize).toList()
        
        // 주사위 굴리는 모든 경우의 수 구하기
        val ls = mutableListOf<List<Int>>()
        repeat(halfDiceSize) {
            ls.add(listOf(1,2,3,4,5,6))
        }
        val possibleDiceRerolls = cartesianProduct(ls.toList())
        
        // 주사위 굴리기
        var maxCount = 0
        for(i in 1 .. comb.size / 2) {
            // A 주사위
            val aDice = comb[i - 1]
            
            // B 주사위
            val bDice = comb[comb.size - i]
            
            var sumAList = mutableListOf<Int>()
            var sumBList = mutableListOf<Int>()
            possibleDiceRerolls.forEach { reroll -> 
                var sumA = 0
                var sumB = 0
                reroll.forEachIndexed { idx, v ->
                    sumA += dice[aDice[idx] - 1][v - 1]
                    sumB += dice[bDice[idx] - 1][v - 1]
                }
                sumAList.add(sumA)
                sumBList.add(sumB)
            }
            
            var winACount = 0
            var winBCount = 0
            sumBList.sort()
            
            for(i in sumAList.indices) {
                var index = sumBList.binarySearch(sumAList[i])
                var index2 = index
                // println(index)
                if(index < 0) {
                    winACount += -(index + 1)
                    winBCount += index + 1 + sumBList.size
                    continue
                }
                while(index > 0) {
                    if(sumBList[index - 1] == sumAList[i]) {
                        index--
                    } else {
                        break
                    }
                }
                
                while(index2 < sumAList.size - 1) {
                    if(sumBList[index2 + 1] == sumAList[i]) {
                        index2++
                    } else {
                        break
                    }
                }
            
                winACount += index 
                winBCount += sumBList.size - index2 - 1
            }
            
            
            if(winACount > winBCount) {
                if(maxCount < winACount) {
                    maxCount = winACount
                    answer = aDice.toIntArray()
                }
            } else if(winACount < winBCount) {
                if(maxCount < winBCount) {
                    maxCount = winBCount
                    answer = bDice.toIntArray()
                }
            }
        }
        return answer
    }
    
    fun combination(
        list: List<Int>,
        k: Int
    ): Sequence<List<Int>> = sequence {
        if(k == 0) {
            yield(emptyList())
        } else {
            for(i in list.indices) {
                val el = list[i]
                val remaining = list.subList(i + 1, list.size)
                for(comb in combination(remaining, k - 1)) {
                    yield(listOf(el) + comb)
                }
            }
        }
    }
    
    fun cartesianProduct(
        lists: List<List<Int>>
    ): List<List<Int>> = lists.fold(listOf(listOf())) {acc, list ->
        acc.flatMap { prev -> list.map {el -> prev + el}}
    }
}