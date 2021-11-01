import java.security.InvalidParameterException;
import java.util.Deque;
import java.util.LinkedList;

public class SquareBracketBalancer {
    String result;
    int swaps;

    SquareBracketBalancer(String string) {
        Deque<Integer> stackRight = new LinkedList<>();
        Deque<Integer> stackLeft = new LinkedList<>();
        char[] characters = string.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            if (characters[i] == '[') {
                stackRight.push(i);
            } else if (characters[i] == ']') {
                if (!stackRight.isEmpty()) {
                    stackRight.pop();
                } else {
                    stackLeft.push(i);
                }
            }
        }
        if (stackLeft.size() != stackRight.size()) {
            throw new InvalidParameterException("The string can't be balanced.");
        }
        swaps = (stackRight.size() + 1) / 2;
        for (int k = 0; k < swaps; k++) {
            int i = stackRight.pop();
            int j = stackLeft.removeLast();
            char t = characters[i];
            characters[i] = characters[j];
            characters[j] = t;
            if (swaps - k > 1) {
                stackRight.pop();
                stackLeft.removeLast();
            }
        }
        result = new String(characters);
    }
}
