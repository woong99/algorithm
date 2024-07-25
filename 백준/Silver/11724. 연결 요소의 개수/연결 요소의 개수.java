import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();

            if (m == 0) {
                System.out.println(n);
                return;
            }

            int[][] graph = new int[n][n];
            boolean[] visited = new boolean[n];

            for (int i = 0; i < m; i++) {
                int u = scanner.nextInt() - 1;
                int v = scanner.nextInt() - 1;

                graph[u][v] = 1;
                graph[v][u] = 1;
            }

            int count = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[j]) {
                        dfs(graph, visited, j);
                        count++;
                    }
                }
            }
            System.out.println(count);
        }
    }

    private static void dfs(int[][] graph, boolean[] visited, int n) {
        visited[n] = true;

        for (int i = 0; i < graph.length; i++) {
            if (graph[n][i] == 1 && !visited[i]) {
                dfs(graph, visited, i);
            }
        }
    }
}
