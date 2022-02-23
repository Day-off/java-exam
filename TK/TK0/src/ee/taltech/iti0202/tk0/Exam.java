package ee.taltech.iti0202.tk0;

import java.util.*;
import java.util.stream.Stream;

public class Exam {


    /**
     * Return a list that contains the exact same numbers as the given list, but rearranged so that
     * all the even numbers come before all the odd numbers. Other than that, the numbers can be in
     * any order. You may modify and return the given list, or make a new list.
     * <p>
     * <p>
     * evenOdd([1, 0, 1, 0, 0, 1, 1]) → [0, 0, 0, 1, 1, 1, 1]
     * evenOdd([3, 3, 2]) → [2, 3, 3]
     * evenOdd([2, 2, 2]) → [2, 2, 2]
     */
    public static List<Integer> evenOdd(List<Integer> nums) {
        List<Integer> odd = nums.stream().filter(a -> a % 2 == 0).toList();
        List<Integer> even = nums.stream().filter(a -> a % 2 != 0).toList();
        return Stream.of(odd, even).flatMap(Collection::stream).toList();
    }


    /**
     * Given 3 int values, a b c, return their sum. However, if one of the values is the same as another of the values,
     * it does not count towards the sum.
     * <p>
     * loneSum(1, 2, 3) → 6
     * loneSum(3, 2, 3) → 2
     * loneSum(3, 3, 3) → 0
     */
    public static int loneSum(int a, int b, int c) {
        if (a != b && b != c && c != a) {
            return a + b + c;
        } else if (b == c && c == a) {
            return 0;
        } else {
            if (a - b == 0) {
                return c;
            } else if (a - c == 0) {
                return b;
            } else {
                return a;
            }
        }
    }


    /**
     * A sandwich is two pieces of bread with something in between. Return the string that is between the first and
     * last appearance of "bread" in the given string, or return the empty string ""
     * if there are not two pieces of bread.
     * <p>
     * getSandwich("breadjambread") → "jam"
     * getSandwich("xxbreadjambreadyy") → "jam"
     * getSandwich("xxbreadyy") → ""
     */
    public static String getSandwich(String str) {
        if (!str.contains("bread")){
            return "";
        }
        int indexStart = str.indexOf("bread")+5;
        int indexFinish = str.indexOf("bread", indexStart);
        if (indexFinish == -1){
            return "";
        }
        return str.substring(indexStart, indexFinish);
    }


    /**
     * Given a map of food keys and topping values, modify and return the map as follows: if the key
     * "ice cream" is present, set its value to "cherry". In all cases, set the key "bread"
     * to have the value "butter".
     * <p>
     * <p>
     * topping({"ice cream": "peanuts"}) → {"bread": "butter", "ice cream": "cherry"}
     * topping({}) → {"bread": "butter"}
     * topping({"pancake": "syrup"}) → {"bread": "butter", "pancake": "syrup"}
     */
    public static Map<String, String> topping(Map<String, String> map) {
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getSandwich("breadjambutterbread"));
        System.out.println(getSandwich("bjjbreadjambreadfgfg"));
        System.out.println(getSandwich("xxbreadjambreadxx"));
        System.out.println(getSandwich("xxbreadjambrx"));
        System.out.println(getSandwich("xxbredjambrx"));



    }


}