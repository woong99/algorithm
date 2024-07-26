import java.util.Scanner;

public class Main {

    private static final int[] dx = {1, -1, 0, 0};

    private static final int[] dy = {0, 0, 1, -1};

    private static int[][] graph;

    private static boolean[][] visited;

    private static int maxWater = 0;

    private static int resultCnt = 0;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();

            graph = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int water = scanner.nextInt();
                    graph[i][j] = water;
                    maxWater = Math.max(maxWater, water);
                }
            }

            for (int i = 0; i <= maxWater; i++) {
                visited = new boolean[n][n];
                int count = 0;
                for (int y = 0; y < n; y++) {
                    for (int x = 0; x < n; x++) {
                        if (!visited[y][x] && graph[y][x] > i) {
                            dfs(x, y, i);
                            count++;
                        }
                    }
                }
                resultCnt = Math.max(resultCnt, count);
            }

            System.out.println(resultCnt);
        }
    }

    private static void dfs(int x, int y, int n) {
        visited[y][x] = true;

        for (int i = 0; i < 4; i++) {
            int cx = x + dx[i];
            int cy = y + dy[i];

            if (cx >= 0 && cy >= 0 && cx < visited.length && cy < visited.length && !visited[cy][cx] && graph[cy][cx] > n) {
                dfs(cx, cy, n);
            }
        }
    }
}
