import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int k;

    private static int[] inputs;

    private static int[] arr;

    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer tokens = new StringTokenizer(br.readLine());
            k = Integer.parseInt(tokens.nextToken());
            if (k == 0) {
                break;
            }

            inputs = new int[k];
            visited = new boolean[k];
            arr = new int[6];

            for (int i = 0; i < k; i++) {
                inputs[i] = Integer.parseInt(tokens.nextToken());
            }

            dfs(0);
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    private static void dfs(int depth) throws IOException {
        if (depth == 6) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                sb.append(arr[i]);
                if (i != 5) {
                    sb.append(" ");
                }
            }

            bw.write(sb.toString());
            bw.newLine();
            return;
        }

        for (int i = 0; i < k; i++) {
            if (!visited[i] && (depth == 0 || arr[depth - 1] <= inputs[i])) {
                visited[i] = true;
                arr[depth] = inputs[i];
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }
}