import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class Solution {

    public int[] solution(int[] answers) {
        int[] answer = {};

        int a1 = 0;
        int a2 = 0;
        int a3 = 0;

        for (int i = 0; i < answers.length; i++) {
            if (i % 5 == 0 && answers[i] == 1) {
                a1++;
            } else if (i % 5 == 1 && answers[i] == 2) {
                a1++;
            } else if (i % 5 == 2 && answers[i] == 3) {
                a1++;
            } else if (i % 5 == 3 && answers[i] == 4) {
                a1++;
            } else if (i % 5 == 4 && answers[i] == 5) {
                a1++;
            }

            if ((i % 8 == 0 || i % 8 == 2 || i % 8 == 4 || i % 8 == 6) && answers[i] == 2) {
                a2++;
            } else if (i % 8 == 1 && answers[i] == 1) {
                a2++;
            } else if (i % 8 == 3 && answers[i] == 3) {
                a2++;
            } else if (i % 8 == 5 && answers[i] == 4) {
                a2++;
            } else if (i % 8 == 7 && answers[i] == 5) {
                a2++;
            }

            if ((i % 10 == 0 || i % 10 == 1) && answers[i] == 3) {
                a3++;
            } else if ((i % 10 == 2 || i % 10 == 3) && answers[i] == 1) {
                a3++;
            } else if ((i % 10 == 4 || i % 10 == 5) && answers[i] == 2) {
                a3++;
            } else if ((i % 10 == 6 || i % 10 == 7) && answers[i] == 4) {
                a3++;
            } else if ((i % 10 == 8 || i % 10 == 9) && answers[i] == 5) {
                a3++;
            }
        }

        List<Integer> ls = Arrays.asList(a1, a2, a3);
        int max = ls.stream()
            .mapToInt(d -> d)
            .max()
            .getAsInt();

        List<Integer> result = new ArrayList<>();
        if (a1 == max) {
            result.add(1);
        }
        if (a2 == max) {
            result.add(2);
        }
        if (a3 == max) {
            result.add(3);
        }
        return result.stream()
            .mapToInt(d -> d)
            .toArray();
    }
}