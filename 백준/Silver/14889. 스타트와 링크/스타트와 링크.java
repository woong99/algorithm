import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int n;

    private static int[][] graph;

    private static int[] arr;

    private static boolean[] visited;

    private static List<int[]> ls = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tokens.nextToken());
        graph = new int[n][n];
        visited = new boolean[n];
        arr = new int[n / 2];

        for (int i = 0; i < n; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }

        dfs(0);
        ls = ls.subList(0, ls.size() / 2);

        int result = Integer.MAX_VALUE;

        for (int[] i : ls) {
            int sum1 = 0;
            int sum2 = 0;
            for (int y : i) {
                for (int x : i) {
                    sum1 += graph[y][x];
                }
            }

            int[] remain = new int[n / 2];
            List<Integer> ls = new ArrayList<>();
            for (int y : i) {
                ls.add(y);
            }

            int cnt = 0;
            for (int k = 0; k < n; k++) {
                if (!ls.contains(k)) {
                    remain[cnt] = k;
                    cnt++;
                }
            }

            for (int y : remain) {
                for (int x : remain) {
                    sum2 += graph[y][x];
                }
            }

            result = Math.min(Math.abs(sum1 - sum2), result);
        }

        System.out.println(result);
        bw.flush();
        bw.close();
    }

    private static void dfs(int r) {
        if (r == n / 2) {
            ls.add(Arrays.copyOf(arr, arr.length));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i] && (r == 0 || arr[r - 1] <= i)) {
                visited[i] = true;
                arr[r] = i;
                dfs(r + 1);
                visited[i] = false;
            }
        }
    }

    private static void dfs1(int r) {
        if (r == 2) {
            System.out.println(Arrays.toString(arr));
            return;
        }

        for (int i = 0; i < n / 2; i++) {
            if (!visited[i] && (r == 0 || arr[r - 1] <= i)) {
                visited[i] = true;
                arr[r] = i;
                dfs1(r + 1);
                visited[i] = false;
            }
        }
    }
}