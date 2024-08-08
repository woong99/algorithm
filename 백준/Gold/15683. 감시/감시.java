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

    private static int m;

    private static int[][] graph;

    private static List<Node> nodes = new ArrayList<>();

    private static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tokens.nextToken());
        m = Integer.parseInt(tokens.nextToken());

        graph = new int[n][m];
        for (int i = 0; i < n; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int token = Integer.parseInt(tokens.nextToken());
                graph[i][j] = token;

                if (token != 0 && token != 6) {
                    nodes.add(new Node(j, i, token));
                }
            }
        }

        dfs(0);
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    private static void dfs(int depth) {
        if (depth == nodes.size()) {
            resetGraph();
            for (Node node : nodes) {
                int num = node.getNum();
                int selected = node.getSelected();
                int x = node.getX();
                int y = node.getY();
                if (num == 1) {
                    if (selected == 1) {
                        goRight(x, y);
                    } else if (selected == 2) {
                        goLeft(x, y);
                    } else if (selected == 3) {
                        goDown(x, y);
                    } else {
                        goUp(x, y);
                    }
                } else if (num == 2) {
                    if (selected == 1) {
                        goRight(x, y);
                        goLeft(x, y);
                    } else {
                        goUp(x, y);
                        goDown(x, y);
                    }
                } else if (num == 3) {
                    if (selected == 1) {
                        goUp(x, y);
                        goRight(x, y);
                    } else if (selected == 2) {
                        goRight(x, y);
                        goDown(x, y);
                    } else if (selected == 3) {
                        goDown(x, y);
                        goLeft(x, y);
                    } else {
                        goLeft(x, y);
                        goUp(x, y);
                    }
                } else if (num == 4) {
                    if (selected == 1) {
                        goUp(x, y);
                        goLeft(x, y);
                        goRight(x, y);
                    } else if (selected == 2) {
                        goUp(x, y);
                        goRight(x, y);
                        goDown(x, y);
                    } else if (selected == 3) {
                        goLeft(x, y);
                        goDown(x, y);
                        goRight(x, y);
                    } else {
                        goUp(x, y);
                        goDown(x, y);
                        goLeft(x, y);
                    }
                } else {
                    goUp(x, y);
                    goDown(x, y);
                    goLeft(x, y);
                    goRight(x, y);
                }
            }

            getResult();
            return;
        }

        Node node = nodes.get(depth);
        for (int i : node.getArr()) {
            node.setSelected(i);
            dfs(depth + 1);
        }
    }

    private static void getResult() {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] == 0) {
                    cnt++;
                }
            }
        }
        result = Math.min(result, cnt);
    }

    private static void resetGraph() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] == -1) {
                    graph[i][j] = 0;
                }
            }
        }
    }

    private static void goLeft(int x, int y) {
        for (int i = x - 1; i >= 0; i--) {
            if (graph[y][i] == 6) {
                break;
            } else if (graph[y][i] == 0) {
                graph[y][i] = -1;
            }
        }
    }

    private static void goRight(int x, int y) {
        for (int i = x + 1; i < m; i++) {
            if (graph[y][i] == 6) {
                break;
            } else if (graph[y][i] == 0) {
                graph[y][i] = -1;
            }
        }
    }

    private static void goUp(int x, int y) {
        for (int i = y - 1; i >= 0; i--) {
            if (graph[i][x] == 6) {
                break;
            } else if (graph[i][x] == 0) {
                graph[i][x] = -1;
            }
        }
    }

    private static void goDown(int x, int y) {
        for (int i = y + 1; i < n; i++) {
            if (graph[i][x] == 6) {
                break;
            } else if (graph[i][x] == 0) {
                graph[i][x] = -1;
            }
        }
    }
}

class Node {

    private final int x;

    private final int y;

    private final int num;

    private final int[] arr;

    private int selected;

    public Node(int x, int y, int num) {
        this.x = x;
        this.y = y;
        this.num = num;
        if (num == 1) {
            this.arr = new int[]{1, 2, 3, 4};
        } else if (num == 2) {
            this.arr = new int[]{1, 2};
        } else if (num == 3) {
            this.arr = new int[]{1, 2, 3, 4};
        } else if (num == 4) {
            this.arr = new int[]{1, 2, 3, 4};
        } else {
            this.arr = new int[]{1};
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getNum() {
        return num;
    }

    public int[] getArr() {
        return arr;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "Node{" +
            "x=" + x +
            ", y=" + y +
            ", num=" + num +
            ", arr=" + Arrays.toString(arr) +
            ", selected=" + selected +
            '}';
    }
}