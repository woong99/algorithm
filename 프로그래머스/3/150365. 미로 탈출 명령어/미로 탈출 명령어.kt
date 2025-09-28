import java.util.*
import kotlin.math.abs

class Solution {   
    val dx = arrayOf(1, 0, 0, -1)
    val dy = arrayOf(0, -1, 1, 0)
    var found = false
    var answer = "impossible"
    
    fun solution(n: Int, m: Int, x: Int, y: Int, r: Int, c: Int, k: Int): String {
        dfs(x - 1, y - 1, r - 1, c - 1, k, 0, n, m, StringBuilder())
        return answer
    }
    
    fun dfs(
        startX: Int,
        startY: Int,
        endX: Int,
        endY: Int,
        distance: Int,
        nextDistance: Int,
        n: Int,
        m: Int,
        sb: StringBuilder
    ) {
        if(found) return
        
        val remain = abs(startX - endX) + abs(startY - endY)
        
        if(remain > distance - nextDistance) return
        if(((distance - nextDistance) - remain) % 2 != 0) return
                
        if(nextDistance == distance && startX == endX && startY == endY) {
            answer = sb.toString()
            found = true
            return
        }
        
        for(i in 0..3) {
            val nx = startX + dx[i]
            val ny = startY + dy[i]

            if(nx >= 0 && nx < n && ny >= 0 && ny < m && nextDistance <= distance) {
                val nextS = when(i) {
                    0 -> "d"
                    1 -> "l"
                    2 -> "r"
                    3 -> "u"
                    else -> ""
                }
                
                sb.append(nextS)
                dfs(nx, ny, endX, endY, distance, nextDistance + 1, n, m, sb)
                sb.deleteCharAt(sb.length - 1) 
                if(found) return
            }
        }
    }
}