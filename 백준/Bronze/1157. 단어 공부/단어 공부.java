import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next().toUpperCase();

        Map<Character, Integer> map = new HashMap<>();
        for (char c : input.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        int max = 0;
        for (Entry<Character, Integer> entry : map.entrySet()) {
            max = Math.max(max, entry.getValue());
        }

        List<Character> list = new ArrayList<>();

        for (Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == max) {
                list.add(entry.getKey());
            }
        }
        if (list.size() != 1) {
            System.out.println("?");
        } else {
            System.out.println(list.get(0));
        }
    }
}
