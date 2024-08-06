import java.util.Scanner;

public class Main {

    private static int n;

    private static int m;

    private static boolean[] visited;

    private static int[] arr;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            n = scanner.nextInt();
            m = scanner.nextInt();

            visited = new boolean[n];
            arr = new int[m];

            dfs(0, 0);
        }
    }

    private static void dfs(int num, int r) {
        if (r == m) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i]);
                if (i != arr.length - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
            return;
        }

        for (int i = num; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[r] = i + 1;
                dfs(i + 1, r + 1);
                visited[i] = false;
            }
        }
    }
}