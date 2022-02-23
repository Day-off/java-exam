package ee.taltech.iti0202.tk1;

import java.awt.image.Kernel;
import java.util.*;
import java.util.stream.IntStream;

public class Exam {


    /**
     * Return the "centered" average of an array of ints, which we'll say is the mean average of the values,
     * except ignoring the largest and smallest values in the array. If there are multiple copies of the
     * smallest value, ignore just one copy, and likewise for the largest value. Use int division to produce
     * the final average. You may assume that the array is length 3 or more.
     * <p>
     * centeredAverage([1, 2, 3, 4, 100]) → 3
     * centeredAverage([1, 1, 5, 5, 10, 8, 7]) → 5
     * centeredAverage([-10, -4, -2, -4, -2, 0]) → -3
     */
    public static int centeredAverage(List<Integer> nums) {
        List<Integer> copy = new ArrayList<>(nums);
        Integer minnum = copy.stream().min(Comparator.comparing(Integer::valueOf)).get();
        Integer maxnum = copy.stream().max(Comparator.comparing(Integer::valueOf)).get();
        nums.remove(Integer.valueOf(minnum));
        nums.remove(Integer.valueOf(maxnum));
        int sum = copy.stream().mapToInt(Integer::intValue).sum();

        return sum / copy.size();
    }


    /**
     * Given 2 int values greater than 0, return whichever value is nearest to 21 without going over.
     * Return 0 if they both go over.
     * <p>
     * blackjack(19, 21) → 21
     * blackjack(21, 19) → 21
     * blackjack(19, 22) → 19
     */
    public static int blackjack(int a, int b) {
        if (a > 21 && b > 21) {
            return 0;
        }
        if (a > 21) {
            return b;
        }
        if (b > 21) {
            return a;
        } else {
            if (21 - a > 21 - b) {
                return b;
            } else {
                return a;
            }
        }
    }


    /**
     * Given a string and an int n, return a string made of n repetitions of the last n characters
     * of the string. You may assume that n is between 0 and the length of the string, inclusive.
     * <p>
     * repeatEnd("Hello", 3) → "llollollo"
     * repeatEnd("Hello", 2) → "lolo"
     * repeatEnd("Hello", 1) → "o"
     */
    public static String repeatEnd(String str, int n) {
        String s = str.substring(str.length() - n);
        return s.repeat(n);

    }

    /**
     * Modify and return the given map as follows: if the keys "a" and "b" are both in the map
     * and have equal values, remove them both.
     * <p>
     * mapAB({"a": "aaa", "b": "aaa", "c": "cake"}) → {"c": "cake"}
     * mapAB({"a": "aaa", "b": "bbb"}) → {"a": "aaa", "b": "bbb"}
     * mapAB({"a": "aaa", "b": "bbb", "c": "aaa"}) → {"a": "aaa", "b": "bbb", "c": "aaa"}
     */
    public static Map<String, String> mapAB(Map<String, String> map) {
        if (map.size() == 1) {
            return map;
        }
        Map<String, String> res = new HashMap<>();
        Collection<String> val = map.values();
        ArrayList<String> al = new ArrayList<String>(val);
        int ind = 0;
        for (Map.Entry<String, String> elem : map.entrySet()) {
            if (ind + 1 < val.size()) {
                ind += 1;
            } else {
                if (!Objects.equals(elem.getValue(), al.get(ind - 1))) {
                    res.put(elem.getKey(), elem.getValue());
                }
            }
            if (!Objects.equals(elem.getValue(), al.get(ind))) {
                if (ind >= 2) {
                    if (!Objects.equals(elem.getValue(), al.get(ind - 2))) {
                        res.put(elem.getKey(), elem.getValue());
                    }
                } else {
                    res.put(elem.getKey(), elem.getValue());
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Map<String, String> res = new HashMap<>();
        res.put("a", "aka");
        res.put("b", "aaa");
        res.put("c", "ana");
        res.put("d", "ana");
        res.put("f", "ana");


        System.out.println(mapAB(res));
    }
}