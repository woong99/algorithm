import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    private static final Set<Integer> infectedComputers = new HashSet<>();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int computerCount = scanner.nextInt(); // 컴퓨터 수
            int computerCoupleCount = scanner.nextInt(); // 컴퓨터의 쌍 수

            int[][] graph = new int[computerCount][computerCount]; // 그래프 선언
            boolean[] visited = new boolean[computerCount]; // 방문 여부 그래프 선언

            for (int i = 0; i < computerCoupleCount; i++) {
                int firstNum = scanner.nextInt();
                int secondNum = scanner.nextInt();
                graph[firstNum - 1][secondNum - 1] = 1;
                graph[secondNum - 1][firstNum - 1] = 1;
            }

            dfs(graph, visited, 0);

            System.out.println(infectedComputers.size());
        }
    }

    private static void dfs(int[][] graph, boolean[] visited, int n) {
        visited[n] = true;
        for (int i = 0; i < graph.length; i++) {
            if (graph[n][i] == 1 && !visited[i]) {
                infectedComputers.add(i);
                dfs(graph, visited, i);
            }
        }
    }
}
