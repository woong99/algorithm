import java.util.Arrays;

class Solution {

    public int[] solution(int brown, int yellow) {
        int i = (int) Math.round((double) yellow / 2);
        int n;

        while (true) {
            if (yellow % i == 0) {
                n = yellow / i;
                if ((i + 2) * (n + 2) - i * n == brown) {
                    break;
                }
            }
            
            i--;
        }
        i = i + 2;
        n = n + 2;
        int max = Math.max(i, n);
        int min = Math.min(i, n);
        return new int[]{max, min};
    }
}