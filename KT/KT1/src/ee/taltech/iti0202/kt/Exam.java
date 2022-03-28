package ee.taltech.iti0202.kt;

import java.util.Locale;

public class Exam {
    /**
     * Given two strings,
     * find if one string is a rotation of another string.
     * Comparison should be case insensitive ("A" and "a" are the same).
     * <p>
     * rotatedString("piimavunts", "ntspiimavu") => true
     * rotatedString("ABC", "cab") => true
     * rotatedString("kurgid", "gikur") => false
     */
    public static boolean rotatedString(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        for (char c1 : str1.toLowerCase(Locale.ROOT).toCharArray()) {
            int count = 0;
            for (char c2 : str2.toLowerCase(Locale.ROOT).toCharArray()) {
                if (c1 == c2) {
                    count += 1;
                }
            }
            if (count == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Given a string, consider the prefix string made of the first N chars of the string.
     * Does that prefix string appear somewhere else in the string.
     * Assume that the string is not empty and that N is in the range 1 .. str.length().
     * The duplicate can overlap with the prefix (but not 100%).
     * See the last two examples.
     * <p>
     * prefixExistsAgain("abXXabc", 1) => true
     * prefixExistsAgain("abXXabc", 2) => true
     * prefixExistsAgain("abXXabc", 3) => false
     * prefixExistsAgain("ababa", 3) => true
     * prefixExistsAgain("aaaa", 3) => true
     * prefixExistsAgain("aaaa", 4) => false
     */
    public static boolean prefixExistsAgain(String str, int n) {
        if (str.isEmpty() || n <= 0) {
            return false;
        }
        String prefix = str.substring(0, n);
        String withOutP = str.substring(n);

        if (withOutP.contains(prefix)) {
            return true;
        } else if (withOutP.length() < prefix.length()) {
            return withOutP.contains(prefix.substring(0, withOutP.length()));
        } else {
            return false;
        }

    }

    public static void main(String[] args) {
        System.out.println(prefixExistsAgain("abXXabc", 1));
        System.out.println(prefixExistsAgain("abXXabc", 2));
        System.out.println(prefixExistsAgain("abXXabc", 3));
        System.out.println(prefixExistsAgain("ababa", 3));
        System.out.println(prefixExistsAgain("aaaa", 3));
        System.out.println(prefixExistsAgain("aaaa", 4));
    }
}
