import java.util.Scanner;

public class Main {

    private static final int[] dx = {1, -1, 0, 0};

    private static final int[] dy = {0, 0, 1, -1};

    private static int[][] graph;

    private static int[][] dp;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int m = scanner.nextInt();
            int n = scanner.nextInt();

            graph = new int[m][n];
            dp = new int[m][n];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    graph[i][j] = scanner.nextInt();
                    dp[i][j] = -1;
                }
            }

            System.out.println(dfs(0, 0));
        }
    }

    private static int dfs(int x, int y) {
        if (x == graph[0].length - 1 && y == graph.length - 1) {
            return 1;
        }

        if (dp[y][x] != -1) {
            return dp[y][x];
        }

        dp[y][x] = 0;

        for (int i = 0; i < 4; i++) {
            int cx = x + dx[i];
            int cy = y + dy[i];

            if (cx >= 0 && cy >= 0 && cx < graph[0].length && cy < graph.length && graph[cy][cx] < graph[y][x]) {
                dp[y][x] += dfs(cx, cy);
            }
        }

        return dp[y][x];
    }
}
