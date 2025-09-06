class Solution {
    fun solution(survey: Array<String>, choices: IntArray): String {
        var answer: String = ""
        
        val map = mutableMapOf<Char, Int>()
        map['R'] = 0
        map['T'] = 0
        map['C'] = 0
        map['F'] = 0
        map['J'] = 0
        map['M'] = 0
        map['A'] = 0
        map['N'] = 0
        
        survey.forEachIndexed { idx , s -> 
            val first = s[0]
            val second = s[1]
            
            when (choices[idx]) {
                1 -> map[first] = map[first]!! + 3
                2 -> map[first] = map[first]!! + 2
                3 -> map[first] = map[first]!! + 1
                5 -> map[second] = map[second]!! + 1
                6 -> map[second] = map[second]!! + 2
                7 -> map[second] = map[second]!! + 3
                else -> {}
            }
        }
        
        answer += calculate('R', 'T', map, answer)
        answer += calculate('C', 'F', map, answer)
        answer += calculate('J', 'M', map, answer)
        answer += calculate('A', 'N', map, answer)
       
        return answer
    }
    
    fun calculate(
        first: Char,
        second: Char,
        map: MutableMap<Char, Int>,
        answer: String
    ): Char {
        if(map[first]!! >= map[second]!!) {
            return first
        } else {
            return second
        }  
    }
}