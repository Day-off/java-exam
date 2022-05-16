package ee.taltech.iti0202.exam;

import java.util.List;

public class Exam {

    /**
     * Given a list of numbers, count how many 2-s are alone (no 2 before or after it).
     * <p>
     * countSingleTwos([2, 2, 1, 3]) => 0
     * countSingleTwos([7, 6, 1, 3]) => 0
     * countSingleTwos([2, 2, 1, 2]) => 1
     * countSingleTwos([2, 2, 2, 1, 3, 2, 1, 2]) => 2
     */
    public static int countSingleTwos(List<Integer> numbers) {
        int prev = 0;
        int count = 0;
        for (int i = 0; i < numbers.size(); i++) {
            if (i + 1 < numbers.size()) {
                if (numbers.get(i + 1) != 2 && prev != 2 && numbers.get(i) == 2) {
                    count += 1;
                }
            } else {
                if (prev != 2 && numbers.get(i) == 2) {
                    count += 1;
                }
            }
            prev = numbers.get(i);
        }
        return count;
    }

    /**
     * Write a method that takes a string and decodes it.
     * The string may contain some numbers.
     * All numbers need to be replaced with a corresponding letter from the alphabet.
     * Each number n references to n-th character of the lowercase alphabet (abcdefghijklmnopqrstuvwxyz).
     * If n is out of bounds, then it should start from "a" again. (0, 26 and 52 correspond to "a")
     * <p>
     * Examples:
     * decodeMessage("0") => "a"
     * decodeMessage("0b2d4f6") => "abcdefg"
     * decodeMessage("h8") => "hi"
     * decodeMessage("11o11") => "lol"
     * decodeMessage("h8 th4r30 p17ogramme43") => "hi there programmer"
     * decodeMessage(":14 19h8s 8s 84e45t34n58 54oo37e523423") => ":o this is getting cooler"
     * decodeMessage("This one doesn't need to be changed!") => "This one doesn't need to be changed!"
     *
     * @param message the message that needs to be decoded
     * @return decoded message
     */
    public static String decodeMessage(String message) {
        StringBuilder res = new StringBuilder();
        List<Character> alph = List.of('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
        for (int i = 0; i < message.length(); i++) {
            if (Character.isDigit(message.charAt(i))) {
                int index = Integer.parseInt(String.valueOf(message.charAt(i)));
                if (i + 1 < message.length()) {
                    if (Character.isDigit(message.charAt(i + 1))) {
                        String a = "" + message.charAt(i) + message.charAt(i + 1);
                        if (Integer.parseInt(a) > 25) {
                            res.append(alph.get(Integer.parseInt(a) % 26));
                        } else {
                            res.append(alph.get(Integer.parseInt(a)));
                        }
                        i++;
//                    res.append(Character.toChars(Integer.parseInt(message.substring(i, i + 1))));
                    } else {
                        res.append(alph.get(index));
//                    res.append(Character.toChars(Integer.parseInt(String.valueOf(message.charAt(i)))));
                    }
                } else {
                    res.append(alph.get(index));
//                    res.append(Character.toChars(Integer.parseInt(String.valueOf(message.charAt(i)))));
                }
            } else {
                res.append(message.charAt(i));
            }

        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        System.out.println(decodeMessage("0"));
        System.out.println(decodeMessage("0b2d4f6"));
        System.out.println(decodeMessage("h8"));
        System.out.println(decodeMessage("11o11"));
        System.out.println(decodeMessage("h8 th4r30 p17ogramme43"));
        System.out.println(decodeMessage(":14 19h8s 8s 84e45t34n58 54oo37e523423"));
        System.out.println(decodeMessage("This one doesn't need to be changed!"));
    }
}
