import java.util.Scanner;

public class Main {

    private static final int[] dx = {1, -1, 0, 0};

    private static final int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int caseCount = scanner.nextInt(); // 테스트 케이스 개수

            for (int i = 0; i < caseCount; i++) {
                int width = scanner.nextInt(); // 가로 길이
                int height = scanner.nextInt(); // 세로 길이
                int positionCount = scanner.nextInt(); // 배추가 심어져 있는 위치의 개수

                int[][] graph = new int[height][width]; // 그래프 선언
                boolean[][] visited = new boolean[height][width]; // 방문 여부

                for (int j = 0; j < positionCount; j++) {
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();

                    graph[y][x] = 1;
                }

                int count = 0;

                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        if (!visited[y][x] && graph[y][x] == 1) {
                            dfs(graph, visited, x, y);
                            count++;
                        }
                    }
                }
                System.out.println(count);
            }
        }
    }

    private static void dfs(int[][] graph, boolean[][] visited, int x, int y) {
        visited[y][x] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < graph[0].length && ny < graph.length && graph[ny][nx] == 1 && !visited[ny][nx]) {
                dfs(graph, visited, nx, ny);
            }
        }
    }
}
