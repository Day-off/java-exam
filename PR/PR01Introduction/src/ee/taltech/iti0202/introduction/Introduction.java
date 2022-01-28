package ee.taltech.iti0202.introduction;

import java.sql.Array;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class Introduction {


    /**
     * Method gets two numbers as parameters.
     * Method must answer if the given pair gives bad, normal or good outcome.
     * Outcome is "bad" if any of values is less than 5.
     * Outcome is "good" if one value equals doubled second value.
     * Outcome is "ok" if both values equal at least 5.
     * The priority is as follows: "bad", "good", "ok" (if several cases apply, take the higher / earlier).
     *
     * @param valueOne int
     * @param valueTwo int
     * @return String based on the values of valueOne and valueTwo
     */
    public String howIsOutcome(int valueOne, int valueTwo) {
        if (valueOne < 5 || valueTwo < 5) {
            return "bad";
        } else if (valueOne * 2 == valueTwo || valueTwo * 2 == valueOne) {
            return "good";
        } else {
            return "ok";
        }
    }

    /**
     * Method gets a list of numbers.
     * Return a list containing only even numbers of the given list.
     * If the given list does not contain any even numbers, return an empty list.
     *
     * @param numbers given list that contains numbers.
     * @return list of even numbers.
     */
    public List<Integer> findEvenNumbersList(List<Integer> numbers) {
        List<Integer> lst = new ArrayList<>();
        for (Integer x : numbers) {
            if (x % 2 == 0) {
                lst.add(x);
            }
        }
        return lst;
    }

    /**
     * Method gets an array of numbers.
     * Return an array containing only even numbers of the given array.
     * If the given array does not contain any even numbers, return an empty array.
     * <p>
     * You must not use the previous function in this function!
     *
     * @param numbers given array that contains numbers.
     * @return array of even numbers.
     */
    public int[] findEvenNumbersArray(int[] numbers) {
        List<Integer> lst = new ArrayList<>();
        int index = 0;
        int[] res = new int[0];
        while (index != numbers.length + 1) {
            if (index == numbers.length) {
                res = new int[lst.size()];
                for (int index_r = 0; index_r < lst.size(); index_r++) {
                    res[index_r] = lst.get(index_r);
                }
            } else if (numbers[index] % 2 == 0) {
                lst.add(numbers[index]);
            }
            index += 1;
        }
        return res;
    }

    /**
     * Method gets two Strings as parameters.
     * If two words have the same length, just put them together. If the length is
     * different, remove so many letters from the beginning of the longer word that the two words are the same length, and
     * then put them together.
     * If the first word was longer, return the answer in lower case. If the second word was longer,
     * return the answer in capital letters.
     * If both words are empty or with spaces, return FALSE.
     *
     * @param first  String
     * @param second String
     * @return String based on the values of first and second
     */
    public String findTheString(String first, String second) {
        if ((first == null || first.length() == 0) && (second == null || second.length() == 0)) {
            return "FALSE";
        }
        assert first != null;
        boolean letter = first.length() > second.length();
        while (second.length() != first.length()) {
            if (first.length() > second.length()) {
                first = first.substring(1);
            }
            if (first.length() < second.length()) {
                second = second.substring(1);
            }
        }
        if (first.length() == 0) {
            return "FALSE";
        }
        String str = first + second;
        if (letter) {
            return str.toLowerCase();
        }
        return str.toUpperCase();
    }

    /**
     * Method gets one String as a parameter.
     * In a given string, count the number of characters that appear exactly three times in a row.
     *
     * @param word String
     * @return The number of triples
     */
    public int countTripleChars(String word) {
        return -1;
    }

    /**
     * Run tests.
     *
     * @param args Arguments from command line.
     */
    public static void main(String[] args) {
        Introduction introduction = new Introduction();
        System.out.println(introduction.howIsOutcome(12, 6)); // "bad"

        List<Integer> nums = new ArrayList<>(Arrays.asList(1, 3));
        System.out.println(introduction.findEvenNumbersList(nums)); // [4, 2, 2, -2, 0]

        int[] array = {9, 0, 24, -6, 3};
        System.out.println(Arrays.toString(introduction.findEvenNumbersArray(array))); // [0, 24, -6]

        String result = introduction.findTheString("Good", "afternoon");
        System.out.println(result);  // GOODNOON
        result = introduction.findTheString("Hello", "lo");
        System.out.println(result);  // lolo
        System.out.println(introduction.findTheString("", ""));  // FALSE
        System.out.println(introduction.findTheString("", "   "));  // FALSE
        System.out.println(introduction.findTheString("  ", "a"));  //  a  (with space in front)

        System.out.println(introduction.countTripleChars("aaabbbabbb"));  // 3
        System.out.println(introduction.countTripleChars("aaa"));  // 1
        System.out.println(introduction.countTripleChars("aaaa"));  // 0
        System.out.println(introduction.countTripleChars("aaaabbbabbbcCc"));  // 2
    }
}
