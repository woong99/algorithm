import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static final int N = 9;

    private static final List<ZeroNode> zeroNodes = new ArrayList<>();

    private static int[][] graph;

    private static boolean endFlag = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int token = Integer.parseInt(tokens.nextToken());
                graph[i][j] = token;
                if (token == 0) {
                    zeroNodes.add(new ZeroNode(j, i));
                }
            }
        }

        dfs(0);
        bw.flush();
        bw.close();
    }

    private static void dfs(int depth) throws IOException {
        if (depth == zeroNodes.size()) {
            if (endFlag) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    sb.append(graph[i][j]);
                    if (j != N - 1) {
                        sb.append(" ");
                    }
                }
                sb.append("\n");
            }
            bw.write(sb.toString());
            endFlag = true;
            return;
        }

        ZeroNode zeroNode = zeroNodes.get(depth);
        for (int i = 1; i < 10; i++) {
            int x = zeroNode.getX();
            int y = zeroNode.getY();

            graph[y][x] = i;
            if (isPossible(x, y)) {
                dfs(depth + 1);
            }
            graph[y][x] = 0;
        }

    }

    private static boolean isPossible(int x, int y) {
        boolean[] check = new boolean[N + 1];

        // X 축 검증
        int[] graphY = graph[y];
        for (int i : graphY) {
            if (i == 0) {
                continue;
            }

            if (check[i]) {
                return false;
            } else {
                check[i] = true;
            }
        }

        // Y 축 검증
        check = new boolean[N + 1];
        for (int i = 0; i < N; i++) {
            if (graph[i][x] == 0) {
                continue;
            }

            if (check[graph[i][x]]) {
                return false;
            } else {
                check[graph[i][x]] = true;
            }
        }

        // 3 X 3 검증
        check = new boolean[N + 1];
        int yy = (y / 3) * 3;
        int xx = (x / 3) * 3;
        for (int i = yy; i < yy + 3; i++) {
            for (int j = xx; j < xx + 3; j++) {
                if (graph[i][j] == 0) {
                    continue;
                }
                if (check[graph[i][j]]) {
                    return false;
                } else {
                    check[graph[i][j]] = true;
                }
            }
        }

        return true;
    }
}

class ZeroNode {

    private final int x;

    private final int y;

    public ZeroNode(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}