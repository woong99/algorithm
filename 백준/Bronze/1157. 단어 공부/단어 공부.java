import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next().toUpperCase();

        Map<Character, Integer> map = new HashMap<>();
        int max = 0;

        for (char c : input.toCharArray()) {
            int count = map.compute(c, (key, value) -> (value == null) ? 1 : value + 1);
            max = Math.max(max, count);
        }

        Character result = null;
        boolean multipleMax = false;

        for (Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == max) {
                if (result != null) {
                    multipleMax = true;
                    break;
                }
                result = entry.getKey();
            }
        }

        System.out.println(multipleMax ? "?" : result);
    }
}
