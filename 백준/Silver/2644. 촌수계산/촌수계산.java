import java.util.Scanner;

public class Main {

    private static int[][] graph;

    private static boolean[] visited;

    private static int b;

    private static int result;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int a = scanner.nextInt();
            b = scanner.nextInt();
            int m = scanner.nextInt();

            graph = new int[n][n];
            visited = new boolean[n];

            for (int i = 0; i < m; i++) {
                int x = scanner.nextInt() - 1;
                int y = scanner.nextInt() - 1;

                graph[x][y] = 1;
                graph[y][x] = 1;
            }

            dfs(a - 1);

            System.out.println(result == 0 ? -1 : result);
        }
    }

    private static int dfs(int n) {
        int count = 1;

        if (n == b - 1) {

            int cc = 0;
            for (boolean b : visited) {
                if (b) {
                    cc++;
                }
            }
            result = cc;
        }

        for (int i = 0; i < graph.length; i++) {
            if (!visited[i] && graph[n][i] == 1) {
                visited[n] = true;
                count += dfs(i);
                visited[n] = false;
            }
        }
        return count;
    }
}
