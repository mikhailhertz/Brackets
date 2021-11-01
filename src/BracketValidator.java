import java.util.Map;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

public class BracketValidator {
    class Bracket {
        public final char character;
        public final char matchingCharacter;
        public final boolean isOpeningBracket;

        Bracket(char character, char matchingCharacter, boolean isOpeningBracket) {
            this.character = character;
            this.matchingCharacter = matchingCharacter;
            this.isOpeningBracket = isOpeningBracket;
        }
    }

    Map<Character, Bracket> map;

    BracketValidator(String brackets) {
        map = new HashMap<>();
        char[] characters = brackets.toCharArray();
        for (int i = 0; i < characters.length; i += 2) {
            Bracket left = new Bracket(characters[i], characters[i + 1], true);
            Bracket right = new Bracket(characters[i + 1], characters[i], false);
            map.put(Character.valueOf(characters[i]), left);
            map.put(Character.valueOf(characters[i + 1]), right);
        }
    }

    public boolean validate(String string) {
        Deque<Bracket> stack = new LinkedList<>();
        char[] characters = string.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            Bracket bracket = map.get(characters[i]);
            if (bracket != null) {
                if (bracket.isOpeningBracket) {
                    stack.push(bracket);
                } else if (stack.isEmpty()) {
                    return false;
                } else {
                    Bracket previousBracket = stack.pop();
                    if (previousBracket.matchingCharacter != bracket.character) {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }
}
