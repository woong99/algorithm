import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int answer = 0;
        
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        
        boolean[][] visited = new boolean[maps.length][maps[0].length];
        visited[0][0] = true;
        Queue<BFS> queue = new LinkedList();
        queue.add(new BFS(0,0,1));
        
        while(!queue.isEmpty()) {
            var bfs = queue.poll();
            if(bfs.x == maps.length - 1 && bfs.y == maps[0].length - 1) {
                return bfs.count;
            }
            
            for(int i = 0; i <= 3; i++) {
                var nx = bfs.x + dx[i];
                var ny = bfs.y + dy[i];
                
                if(nx >= 0 && nx < maps.length && ny >= 0 && ny < maps[0].length && !visited[nx][ny] && maps[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    queue.add(new BFS(nx, ny, bfs.count + 1));
                }
            }
        }
        return -1;
    }
}

class BFS {
    int x;
    int y;
    int count;
    
    public BFS(int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }
}