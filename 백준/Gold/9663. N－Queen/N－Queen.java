import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int n;

    private static int[] graph;

    private static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tokens.nextToken());

        graph = new int[n];

        dfs(0);

        System.out.println(result);

        bw.flush();
        bw.close();
    }

    private static void dfs(int num) {
        if (num == n) {
            result++;
            return;
        }

        for (int i = 0; i < n; i++) {
            graph[num] = i;
            if (isPossible(num)) {
                dfs(num + 1);
            }
        }
    }

    private static boolean isPossible(int n) {
        for (int i = 0; i < n; i++) {
            if (Math.abs(n - i) == Math.abs(graph[n] - graph[i]) || graph[n] == graph[i]) {
                return false;
            }
        }
        return true;
    }
}