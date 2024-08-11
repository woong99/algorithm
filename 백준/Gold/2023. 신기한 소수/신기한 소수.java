import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final Set<Integer> primes = new HashSet<>();
    private static int n;
    private static String[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tokens.nextToken());
        graph = new String[n];

        dfs(0);

        bw.flush();
        bw.close();
    }

    private static void dfs(int stack) throws IOException {
        if (stack == n) {
            bw.write(String.join("", graph));
            bw.newLine();
            return;
        }

        for (int i = 0; i < 10; i++) {
            graph[stack] = String.valueOf(i);
            if (isPossible(stack)) {
                dfs(stack + 1);
            }
        }
    }

    private static boolean isPossible(int stack) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= stack; i++) {
            sb.append(graph[i]);
        }

        return isPrime(Integer.parseInt(sb.toString()));
    }

    private static boolean isPrime(int num) {
        if (num == 0 || num == 1) {
            return false;
        }

        if (primes.contains(num)) {
            return true;
        }

        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        primes.add(num);
        return true;
    }
}