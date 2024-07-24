import java.util.Scanner;

public class Main {

    private static int maxSafeArea = 0;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int height = scanner.nextInt();
            int width = scanner.nextInt();

            int[][] map = new int[height][width];

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    map[i][j] = Integer.parseInt(scanner.next());
                }
            }

            combination(map, 0);
            System.out.println(maxSafeArea);
        }
    }

    private static void combination(int[][] map, int index) {
        if (index == 3) {
            int[][] tempMap = new int[map.length][map[0].length];
            for (int i = 0; i < map.length; i++) {
                System.arraycopy(map[i], 0, tempMap[i], 0, map[0].length);
            }

            // 바이러스 감염
            for (int i = 0; i < tempMap.length; i++) {
                for (int j = 0; j < tempMap[0].length; j++) {
                    if (tempMap[i][j] == 2) {
                        processPollution(tempMap, j, i);
                    }
                }
            }

            int safeArea = 0;
            for (int[] i : tempMap) {
                for (int j = 0; j < tempMap[0].length; j++) {
                    if (i[j] == 0) {
                        safeArea++;
                    }
                }
            }

            maxSafeArea = Math.max(maxSafeArea, safeArea);
            return;
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    combination(map, index + 1);
                    map[i][j] = 0;
                }
            }
        }
    }

    private static void processPollution(int[][] map, int x, int y) {
        if (x < 0 || y < 0 || x >= map[0].length || y >= map.length || map[y][x] != 2) {
            return;
        }

        if (x - 1 >= 0 && map[y][x - 1] == 0) {
            map[y][x - 1] = 2;
            processPollution(map, x - 1, y);
        }

        if (x + 1 < map[0].length && map[y][x + 1] == 0) {
            map[y][x + 1] = 2;
            processPollution(map, x + 1, y);
        }

        if (y - 1 >= 0 && map[y - 1][x] == 0) {
            map[y - 1][x] = 2;
            processPollution(map, x, y - 1);
        }

        if (y + 1 < map.length && map[y + 1][x] == 0) {
            map[y + 1][x] = 2;
            processPollution(map, x, y + 1);
        }
    }
}
