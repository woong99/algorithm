import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final int[] dx = {1, -1, 0, 0};

    private static final int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int mapSize = scanner.nextInt(); // 지도 크기

            int[][] map = new int[mapSize][mapSize]; // 지도 선언
            boolean[][] visited = new boolean[mapSize][mapSize]; // 방문 여부

            for (int i = 0; i < mapSize; i++) {
                String input = scanner.next();
                for (int j = 0; j < mapSize; j++) {
                    map[i][j] = Character.getNumericValue(input.charAt(j));
                }
            }

            int homeCount = 0;
            List<Integer> homeSizes = new ArrayList<>();

            for (int i = 0; i < mapSize; i++) {
                for (int j = 0; j < mapSize; j++) {
                    if (!visited[i][j] && map[i][j] == 1) {
                        int size = dfs(map, visited, j, i);
                        homeSizes.add(size);
                        homeCount++;
                    }
                }
            }

            System.out.println(homeCount);
            Collections.sort(homeSizes);
            for (int size : homeSizes) {
                System.out.println(size);
            }
        }
    }

    private static int dfs(int[][] map, boolean[][] visited, int x, int y) {
        int count = 1;
        visited[y][x] = true;

        for (int direction = 0; direction < 4; direction++) {
            int nx = x + dx[direction];
            int ny = y + dy[direction];

            if (nx >= 0 && ny >= 0 && nx < map.length && ny < map.length && map[ny][nx] == 1 && !visited[ny][nx]) {
                count += dfs(map, visited, nx, ny);
            }
        }
        return count;
    }
}
