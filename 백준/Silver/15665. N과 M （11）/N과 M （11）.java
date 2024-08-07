import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int n;

    private static int m;

    private static int[] arr;

    private static int[] ls;

    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tokens.nextToken());
        m = Integer.parseInt(tokens.nextToken());

        ls = new int[n];
        visited = new boolean[n];
        arr = new int[m];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            ls[i] = Integer.parseInt(tokens.nextToken());
        }

        Arrays.sort(ls);

        dfs(0);

        bw.flush();
        bw.close();
    }

    private static void dfs(int r) throws IOException {
        if (r == m) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < m; i++) {
                sb.append(arr[i]);
                if (i != m - 1) {
                    sb.append(" ");
                }
            }
            bw.write(sb.toString());
            bw.newLine();
            return;
        }

        int before = 0;
        for (int i = 0; i < n; i++) {
            if (before != ls[i]) {
                arr[r] = ls[i];
                before = ls[i];
                dfs(r + 1);
            }
        }
    }
}