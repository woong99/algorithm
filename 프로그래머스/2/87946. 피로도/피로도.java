import java.util.ArrayList;
import java.util.List;

class Solution {

    private int[][] dungeons = null;

    private final List<Integer> cnts = new ArrayList<>();

    public int solution(int k, int[][] dungeons) {
        this.dungeons = dungeons;

        int len = dungeons.length;
        String[] arr = new String[len];
        for (int i = 0; i < len; i++) {
            arr[i] = String.valueOf(i);
        }

        String[] output = new String[len];
        boolean[] visited = new boolean[len];
        permutation(arr, output, visited, 0, len, len, k);

        return this.cnts.stream()
            .mapToInt(d -> d)
            .max()
            .getAsInt();
    }

    private void permutation(String[] arr, String[] output, boolean[] visited, int depth, int n, int r, int k) {
        if (depth == r) {
            int cnt = 0;
            int total = k;
            for (String s : output) {
                int a1 = this.dungeons[Integer.parseInt(s)][0];
                int a2 = this.dungeons[Integer.parseInt(s)][1];
                if (total >= a1) {
                    total -= a2;
                    cnt++;
                }
            }
            this.cnts.add(cnt);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                output[depth] = arr[i];
                permutation(arr, output, visited, depth + 1, n, r, k);
                visited[i] = false;
            }
        }
    }
}

