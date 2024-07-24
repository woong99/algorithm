import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String[] croatiaAlphabets = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};

        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.next();
            
            for (String croatiaAlphabet : croatiaAlphabets) {
                if (input.contains(croatiaAlphabet)) {
                    input = input.replace(croatiaAlphabet, "!");
                }
            }

            System.out.println(input.length());
        }
    }
}
