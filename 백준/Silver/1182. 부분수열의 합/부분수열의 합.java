import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int n;

    private static int s;

    private static int[] inputs;

    private static int[] arr;

    private static boolean[] visited;

    private static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        n = Integer.parseInt(tokens.nextToken());
        s = Integer.parseInt(tokens.nextToken());

        inputs = new int[n];
        visited = new boolean[n];
        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            inputs[i] = Integer.parseInt(tokens.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            arr = new int[i];
            dfs(0, i);
        }

        System.out.println(result);

        bw.flush();
        bw.close();
    }

    private static void dfs(int depth, int r) {
        if (depth == r) {
            calculate();
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i] && (depth == 0 || arr[depth - 1] <= i)) {
                visited[i] = true;
                arr[depth] = i;
                dfs(depth + 1, r);
                visited[i] = false;
            }
        }
    }

    private static void calculate() {
        int sum = 0;
        for (int i : arr) {
            sum += inputs[i];
        }
        if (sum == s) {
            result++;
        }
    }
}