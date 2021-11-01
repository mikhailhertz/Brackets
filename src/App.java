import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class App {
    static String extractBrackets(String string) {
        Set<Character> filter = new HashSet<>(Arrays.asList('{', '}', '[', ']', '(', ')'));
        return string.codePoints().filter(character -> filter.contains((char) character))
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }

    static String randomSquareBrackets(int length) {
        Character[] characters = new Character[length];
        Arrays.fill(characters, 0, length / 2, '[');
        Arrays.fill(characters, length / 2, length, ']');
        List<Character> list = Arrays.asList(characters);
        Collections.shuffle(list);
        char[] charArray = new char[length];
        for (int i = 0; i < length; i++) {
            charArray[i] = list.get(i);
        }
        return new String(charArray);
    }

    public static void main(String[] parameters) throws Exception {
        String string1 = """
                class App {
                    public static void main(String[] arguments) {
                        System.out.println("Hello, world!");
                    }
                }""";
        BracketValidator bv1 = new BracketValidator("{}[]()");
        String string1Brackets = extractBrackets(string1);
        System.out.println("part 1");
        System.out.println(string1Brackets);
        System.out.println("is it valid? " + bv1.validate(string1));
        String string2 = "][[]]]][[][[";
        SquareBracketBalancer sbb = new SquareBracketBalancer(string2);
        BracketValidator bv2 = new BracketValidator("[]");
        System.out.println("\npart 2");
        System.out.println("before");
        System.out.println(string2);
        System.out.println("after " + sbb.swaps + " swaps");
        System.out.println(sbb.result);
        System.out.println("is result valid? " + bv2.validate(sbb.result));
    }
}
