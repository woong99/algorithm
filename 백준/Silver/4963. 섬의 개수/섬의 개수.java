import java.util.Scanner;

public class Main {

    private static final int[] dx = {1, -1, 0, 0, 1, 1, -1, -1};

    private static final int[] dy = {0, 0, 1, -1, 1, -1, 1, -1};

    private static int[][] graph;

    private static boolean[][] visited;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                int w = scanner.nextInt();
                int h = scanner.nextInt();

                if (w == 0 && h == 0) {
                    break;
                }

                graph = new int[h][w];
                visited = new boolean[h][w];

                for (int i = 0; i < h; i++) {
                    for (int j = 0; j < w; j++) {
                        graph[i][j] = scanner.nextInt();
                    }
                }
                
                int count = 0;

                for (int i = 0; i < h; i++) {
                    for (int j = 0; j < w; j++) {
                        if (!visited[i][j] && graph[i][j] == 1) {
                            dfs(j, i);
                            count++;
                        }
                    }
                }

                System.out.println(count);
            }
        }
    }

    private static void dfs(int x, int y) {
        visited[y][x] = true;

        for (int i = 0; i < 8; i++) {
            int cx = x + dx[i];
            int cy = y + dy[i];

            if (cx >= 0 && cy >= 0 && cx < graph[0].length && cy < graph.length && !visited[cy][cx] && graph[y][x] == 1) {
                dfs(cx, cy);
            }
        }
    }
}
