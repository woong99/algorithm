import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Set<Integer> result = new HashSet<>();

        for (int i = 1; i <= 10000; i++) {
            int nonSelfNumber = getNonSelfNumber(i);
            if (nonSelfNumber <= 10000) {
                result.add(nonSelfNumber);
            }
        }

        for (int i = 1; i <= 10000; i++) {
            if (!result.contains(i)) {
                System.out.println(i);
            }
        }
    }

    private static int getNonSelfNumber(int number) {
        int sum = number;
        while (number > 0) {
            sum += number % 10;
            number = number / 10;
        }

        return sum;
    }
}
