import java.util.Scanner;

public class Main {

    private static final int[] dx = {1, -1, 0, 0};

    private static final int[] dy = {0, 0, 1, -1};

    private static int[][] graph;

    private static int[][] afterGraph;

    private static boolean[][] visited;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();

            graph = new int[n][m];
            afterGraph = new int[n][m];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    graph[i][j] = scanner.nextInt();
                }
            }

            int count = 0;
            int result = 0;
            while (count < 2) {
                count = 0;
                result++;
                afterGraph = new int[n][m];
                visited = new boolean[n][m];

                // 빙산 녹이기
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        int cnt = 0;
                        for (int k = 0; k < 4; k++) {
                            int cx = j + dx[k];
                            int cy = i + dy[k];
                            if (cx >= 0 && cy >= 0 && cx < graph[0].length && cy < graph.length && graph[cy][cx] == 0) {
                                cnt++;
                            }
                        }

                        afterGraph[i][j] = Math.max(0, graph[i][j] - cnt);
                    }
                }

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        graph[i][j] = afterGraph[i][j];
                    }
                }

                // 분리 체크
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (!visited[i][j] && afterGraph[i][j] != 0) {
                            count++;
                            dfs(j, i);
                        }
                    }
                }

                // 다 녹은 경우 체크
                int totalCnt = 0;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (afterGraph[i][j] == 0) {
                            totalCnt++;
                        }
                    }
                }
                if (totalCnt == n * m) {
                    System.out.println(0);
                    return;
                }
            }

            System.out.println(result);
        }
    }

    private static void dfs(int x, int y) {
        visited[y][x] = true;

        for (int i = 0; i < 4; i++) {
            int cx = x + dx[i];
            int cy = y + dy[i];

            if (cx >= 0 && cy >= 0 && cx < graph[0].length && cy < graph.length && !visited[cy][cx] && afterGraph[cy][cx] != 0) {
                dfs(cx, cy);
            }
        }
    }
}
