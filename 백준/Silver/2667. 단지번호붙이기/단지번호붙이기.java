import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int mapSize = scanner.nextInt(); // 지도 크기

            int[][] map = new int[mapSize][mapSize]; // 지도 선언
            int[][] visited = new int[mapSize][mapSize]; // 방문 여부

            for (int i = 0; i < mapSize; i++) {
                String input = scanner.next();
                for (int j = 0; j < mapSize; j++) {
                    char c = input.toCharArray()[j];
                    map[i][j] = Character.getNumericValue(c);
                }
            }

            int homeCount = 0;

            for (int i = 0; i < mapSize; i++) {
                for (int j = 0; j < mapSize; j++) {
                    if (visited[i][j] == 0 && map[i][j] == 1) {
                        dfs(map, visited, j, i, ++homeCount);
                    }
                }
            }

            Map<Integer, Integer> homeMap = new HashMap<>();
            for (int i = 0; i < mapSize; i++) {
                for (int j = 0; j < mapSize; j++) {
                    if (visited[i][j] != 0) {
                        homeMap.put(visited[i][j], homeMap.getOrDefault(visited[i][j], 0) + 1);
                    }
                }
            }

            System.out.println(homeCount);
            List<Integer> list = new ArrayList<>(homeMap.values());
            Collections.sort(list);

            for (Integer i : list) {
                System.out.println(i);
            }
        }
    }

    private static void dfs(int[][] map, int[][] visited, int x, int y, int num) {
        visited[y][x] = num;

        if (x + 1 < map.length && map[y][x + 1] == 1 && visited[y][x + 1] == 0) { // 오른쪽
            dfs(map, visited, x + 1, y, num);
        }

        if (x - 1 >= 0 && map[y][x - 1] == 1 && visited[y][x - 1] == 0) { // 왼쪽
            dfs(map, visited, x - 1, y, num);
        }

        if (y + 1 < map.length && map[y + 1][x] == 1 && visited[y + 1][x] == 0) { // 아래쪽
            dfs(map, visited, x, y + 1, num);
        }

        if (y - 1 >= 0 && map[y - 1][x] == 1 && visited[y - 1][x] == 0) { // 위쪽
            dfs(map, visited, x, y - 1, num);
        }
    }
}
