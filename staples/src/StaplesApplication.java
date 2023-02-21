import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;

public class StaplesApplication {
    public static void main(String[] args) throws IOException {
        //Возможность наличия мусора не обговорена поэтому не учитываем
        String staples = readStaples();
        String correctStaples = findCorrectStaples(staples);
        printStaplesResult(correctStaples);
    }

    private static String readStaples() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter Staples: ");
        return br.readLine();
    }

    private static String findCorrectStaples(String staples) {
        int staplesLength = staples.length();
        Stack<Integer> openedStaples = new Stack<>();
        SortedSet<Integer> correctStaplesNumbers = new TreeSet();
        for (int i = 0; i < staplesLength; i++) {
            if (isStapleOpen(staples.charAt(i))) {
                openedStaples.push(i);
            }
            if (!openedStaples.empty() && isStapleClose(staples.charAt(i))) {
                correctStaplesNumbers.add(openedStaples.pop());
                correctStaplesNumbers.add(i);
            }
        }
        StringBuilder result = new StringBuilder();
        for (int correctStapleNumber : correctStaplesNumbers) {
            result.append(staples.charAt(correctStapleNumber));
        }
        return result.toString();
    }

    private static boolean isStapleOpen(char staple) {
        return staple == '(';
    }

    private static boolean isStapleClose(char staple) {
        return staple == ')';
    }

    private static void printStaplesResult(String staples) {
        Integer staplesLength = staples.length();
        StringBuilder result = new StringBuilder(staplesLength.toString());
        if (staplesLength != 0) {
            result.append(" - " + staples);
        }
        System.out.println(result);
    }
}
