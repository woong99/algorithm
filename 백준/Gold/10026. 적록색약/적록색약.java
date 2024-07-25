import java.util.Scanner;

public class Main {

    private static final int[] dx = {1, -1, 0, 0};

    private static final int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();

            char[][] graph = new char[n][n];
            boolean[][] visited = new boolean[n][n];

            for (int i = 0; i < n; i++) {
                String input = scanner.next();
                for (int j = 0; j < input.length(); j++) {
                    graph[i][j] = input.charAt(j);
                }
            }

            int normalCount = 0;
            int specialCount = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        normalDfs(graph, visited, j, i, graph[i][j]);
                        normalCount++;
                    }
                }
            }

            visited = new boolean[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        specialDfs(graph, visited, j, i, graph[i][j]);
                        specialCount++;
                    }
                }
            }

            System.out.println(normalCount + " " + specialCount);
        }
    }

    private static void normalDfs(char[][] graph, boolean[][] visited, int x, int y, char color) {
        visited[y][x] = true;

        for (int i = 0; i < 4; i++) {
            int cx = x + dx[i];
            int cy = y + dy[i];

            if (cx >= 0 && cy >= 0 && cx < visited.length && cy < visited.length && !visited[cy][cx] && graph[cy][cx] == color) {
                normalDfs(graph, visited, cx, cy, color);
            }
        }
    }

    private static void specialDfs(char[][] graph, boolean[][] visited, int x, int y, char color) {
        visited[y][x] = true;

        for (int i = 0; i < 4; i++) {
            int cx = x + dx[i];
            int cy = y + dy[i];

            if (cx >= 0 && cy >= 0 && cx < visited.length && cy < visited.length && !visited[cy][cx]) {
                char nextColor = graph[cy][cx];

                if (color == 'R' && (nextColor == 'R' || nextColor == 'G')) {
                    specialDfs(graph, visited, cx, cy, nextColor);
                } else if (color == 'G' && (nextColor == 'R' || nextColor == 'G')) {
                    specialDfs(graph, visited, cx, cy, nextColor);
                } else if (color == nextColor) {
                    specialDfs(graph, visited, cx, cy, nextColor);
                }
            }
        }
    }
}
