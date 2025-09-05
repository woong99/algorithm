import java.time.*
import java.time.format.*

class Solution {
    fun solution(today: String, terms: Array<String>, privacies: Array<String>): MutableList<Int> {
        var answer = mutableListOf<Int>()
        
        val termsMap = terms.associate {
            val (type, expireDate) =  it.split(" ")
            type to expireDate.toLong()
        }
        
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")

        privacies.forEachIndexed { idx, v ->
            val (collectDate, type) = v.split(" ")
            val maxDate = LocalDate.parse(today, formatter).minusMonths(termsMap[type]!!)
            val parsedCollectDate = LocalDate.parse(collectDate, formatter)
            if(parsedCollectDate <= maxDate) {
                answer.add(idx + 1)
            }
        }
        
        return answer
    }
}