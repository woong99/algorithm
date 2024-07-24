import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            // 입력 수 입력
            int inputCount = scanner.nextInt();

            int[] inputs = new int[inputCount];

            // 값 입력
            for (int i = 0; i < inputCount; i++) {
                inputs[i] = scanner.nextInt();
            }

            // 총합
            int sum = Arrays.stream(inputs).sum();

            // 산술평균
            int arithmeticMean = (int) Math.round((double) sum / inputCount);

            // 입력값 정렬
            Arrays.sort(inputs);

            // 중앙값
            int median = inputs[inputCount / 2];

            // 최빈값
            Map<Integer, Integer> freequencyMap = new HashMap<>();
            for (int input : inputs) {
                freequencyMap.put(input, freequencyMap.getOrDefault(input, 0) + 1);
            }

            int maxFrequency = Collections.max(freequencyMap.values());
            List<Integer> modes = freequencyMap.entrySet().stream()
                .filter(entry -> entry.getValue() == maxFrequency)
                .map(Entry::getKey)
                .sorted()
                .collect(Collectors.toList());

            int mode = modes.size() > 1 ? modes.get(1) : modes.get(0);

            // 범위
            int range = inputs[inputs.length - 1] - inputs[0];

            System.out.println(arithmeticMean);
            System.out.println(median);
            System.out.println(mode);
            System.out.println(range);
        }
    }
}
