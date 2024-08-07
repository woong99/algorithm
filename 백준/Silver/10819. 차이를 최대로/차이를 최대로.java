import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int n;

    private static int[] inputs;

    private static int[] arr;

    private static boolean[] visited;

    private static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tokens.nextToken());

        inputs = new int[n];
        arr = new int[n];
        visited = new boolean[n];
        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            inputs[i] = Integer.parseInt(tokens.nextToken());
        }

        dfs(0);
        System.out.println(result);
        
        bw.flush();
        bw.close();
    }

    private static void dfs(int depth) {
        if (depth == n) {
            int sum = 0;
            for (int i = 0; i < n - 1; i++) {
                sum += Math.abs(inputs[arr[i]] - inputs[arr[i + 1]]);
            }
            result = Math.max(sum, result);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[depth] = i;
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }
}