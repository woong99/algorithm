import java.util.HashSet;
import java.util.Set;

class Solution {

    private final Set<Integer> set = new HashSet<>();

    private int answer = 0;

    public int solution(String numbers) {

        String[] arr = numbers.split("");
        int n = arr.length;

        for (String s : arr) {
            set.add(Integer.valueOf(s));
        }

        for (int i = 2; i <= n; i++) {
            String[] output = new String[n]; // 결과
            boolean[] visited = new boolean[n]; // 방문 여부

            permutation(arr, output, visited, 0, n, i);
        }

        for (Integer i : set) {
            if (i == 0 || i == 1) {
                continue;
            }
            
            int now = 2;
            int cnt = 0;
            while (now <= i) {
                if (i % now == 0) {
                    cnt++;
                }
                now++;
                if (cnt > 1) {
                    break;
                }
            }
            if (cnt == 1) {
                answer++;
            }
        }
        return answer;
    }

    /**
     * 순열
     *
     * @param arr     배열
     * @param output  결과
     * @param visited 방문 여부를 기록하기 위한 배열
     * @param depth   깊이
     * @param n       배열의 길이
     * @param r       뽑고 싶은 개수
     */
    private void permutation(String[] arr, String[] output, boolean[] visited, int depth, int n, int r) {
        if (depth == r) {
            StringBuilder sb = new StringBuilder();
            for (String o : output) {
                if (o != null) {
                    sb.append(o);
                }
                set.add(Integer.valueOf(sb.toString()));
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                output[depth] = arr[i];
                permutation(arr, output, visited, depth + 1, n, r);
                visited[i] = false;
            }
        }
    }
}
