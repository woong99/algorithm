import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<List<Integer>> graph;

    private static int[] visited;

    private static String result;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int k = scanner.nextInt();
            for (int i = 0; i < k; i++) {
                int v = scanner.nextInt();
                int e = scanner.nextInt();

                graph = new ArrayList<>();
                for (int q = 0; q < v; q++) {
                    graph.add(new ArrayList<>());
                }

                visited = new int[v];
                for (int j = 0; j < e; j++) {
                    int x = scanner.nextInt() - 1;
                    int y = scanner.nextInt() - 1;

                    graph.get(x).add(y);
                    graph.get(y).add(x);
                }

                result = "YES";
                for (int j = 0; j < v; j++) {
                    if (visited[j] == 0) {
                        dfs(j, 1);
                    }
                }
                System.out.println(result);
            }
        }
    }

    private static void dfs(int n, int color) {
        visited[n] = color;

        for (Integer neighbor : graph.get(n)) {
            if (visited[neighbor] == 0) {
                dfs(neighbor, color == 1 ? 2 : 1);
            } else if (visited[neighbor] == color) {
                result = "NO";
            }
        }
    }
}
