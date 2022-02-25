package ee.taltech.iti0202.tk2;

import java.util.List;
import java.util.Map;

public class Exam {

    /**
     * Return the sum of the numbers in the array,
     * except ignore sections of numbers
     * starting with a 6 and extending to the next 7
     * (every 6 will be followed by at least one 7).
     * Return 0 for no numbers.
     * <p>
     * sum67([1, 2, 2]) => 5
     * sum67([1, 2, 2, 6, 99, 99, 7]) => 5
     * sum67([1, 1, 6, 7, 2]) => 4
     */
    public static int sum67(List<Integer> numbers) {
        if (numbers.size() == 0) {
            return 0;
        }
        boolean pose = false;
        int sum = 0;
        for (Integer num : numbers) {
            if (!pose) {
                if (num == 6) {
                    pose = true;
                } else {
                    sum += num;
                }
            } else if (num == 7) {
                pose = false;
            }
        }
        return sum;
    }

    /**
     * Given 3 ints, a b c, return the sum of their rounded values.
     * We'll round an int value up to the next multiple of 10
     * if its rightmost digit is 5 or more, so 15 rounds up to 20.
     * Alternately, round down to the previous multiple of 10
     * if its rightmost digit is less than 5, so 12 rounds down to 10
     * <p>
     * roundSum(16, 17, 18) => 60
     * roundSum(12, 13, 14) => 30
     * roundSum(6, 4, 4) => 10
     */
    public static int roundSum(int a, int b, int c) {
        Integer sum = a + b + c;
        String res = Integer.toString(sum);
        Integer last = Integer.parseInt(res.substring(res.length() - 1));
        if (last >= 5) {
            return sum + (10 - last);
        } else {
            return sum - last;
        }
    }

    /**
     * Given a string, compute a new string by moving the first char to come after the next two chars,
     * so "abc" yields "bca".
     * Repeat this process for each subsequent group of 3 chars, so "abcdef" yields "bcaefd".
     * Ignore any group of fewer than 3 chars at the end.
     * <p>
     * oneTwo("abc") => "bca"
     * oneTwo("tca") => "cat"
     * oneTwo("tcagdo") => "catdog"
     * oneTwo("abcd") => "bca"
     * oneTwo("a") => ""
     */
    public static String oneTwo(String str) {
        if (str.length() < 3) {
            return "";
        }
        String news = str.substring(0, str.length() - str.length() % 3);
        List<Character> res = news.chars().mapToObj(e -> (char) e).toList();
        Character one = null;
        StringBuilder result = new StringBuilder();
        int count = 0;
        for (Character cha : res) {
            count += 1;
            if (count == 1) {
                one = cha;
            } else if (count == 2 || count == 3) {
                result.append(cha);
            } else {
                result.append(one);
                one = cha;
                count = 1;
            }
        }
        result.append(one);
        return result.toString();
    }

    /**
     * Modify and return the given map as follows:
     * if exactly one of the keys "a" or "b" exists in the map (but not both),
     * set the other to have that same value in the map.
     * <p>
     * mapAXorB({"a": "aaa", "c": "cake"}) => {"a": "aaa", "b": "aaa", "c": "cake"}
     * mapAXorB({"b": "bbb", "c": "cake"}) => {"a": "bbb", "b": "bbb", "c": "cake"}
     * mapAXorB({"a": "aaa", "b": "bbb", "c": "cake"}) => {"a": "aaa", "b": "bbb", "c": "cake"}
     */
    public static Map<String, String> mapAXorB(Map<String, String> map) {
        if (map.containsKey("b") && map.containsKey("a") || (!map.containsKey("a") && !map.containsKey("b"))) {
            return map;
        } else if (map.containsKey("b")) {
            map.put("a", map.get("b"));
            return map;
        } else {
            map.put("b", map.get("a"));
            return map;
        }
    }

    public static void main(String[] args) {
        System.out.println(roundSum(16, 17, 18));
        System.out.println(roundSum(12, 13, 14));
        System.out.println(roundSum(6, 4, 4));
    }
}
