import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        StringBuilder correctStaples = new StringBuilder();
        for (int i = 0; i < staplesLength; i++) {
            boolean findedFirstOpenStaple = false;
            int stapleOpenCount = 0;
            int stapleCloseCount = 0;
            int firstStaple = 0;
            for (int j = i; j < staplesLength; j++) {
                if (isStapleOpen(staples.charAt(j))) {
                    if (!findedFirstOpenStaple) {
                        findedFirstOpenStaple = true;
                        firstStaple = j;
                    }
                    stapleOpenCount++;

                } else if (!findedFirstOpenStaple) {
                    continue;
                }
                if (isStapleClose(staples.charAt(j))) {
                    stapleCloseCount++;
                }
                if (stapleOpenCount > 0 && stapleCloseCount > 0 && stapleOpenCount == stapleCloseCount) {
                    correctStaples.append(staples.substring(firstStaple, j + 1));
                    i = j + 1;
                    findedFirstOpenStaple = false;
                    continue;
                }
            }
        }
        return correctStaples.toString();
    }

    private static boolean isStapleOpen(char staple) {
        if (staple == '(') {
            return true;
        }
        return false;
    }

    private static boolean isStapleClose(char staple) {
        if (staple == ')') {
            return true;
        }
        return false;
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
