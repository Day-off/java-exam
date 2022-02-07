package ee.taltech.iti0202.datastructures;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;





public class DataStructures {

    public HashMap<String, Integer> studentInfot = new HashMap<>();


    /**
     * Given String is a sentence with some words.
     * There are only single whitespace between every word and no punctuation marks.
     * Also there are no capital letters in input string.
     * <p>
     * Return the longest word from the input sentence.
     * <p>
     * If there are more than one word with the same length then return the word which comes alphabetically first.
     * <p>
     * Hints:
     * You can split words into an array using "str.split()"
     * Sorting the list with the longest words can definitely the word which comes alphabetically first.
     *
     * @param sentence input String to find the longest words
     * @return the longest String from input
     */
    public static String findLongestWord(String sentence) {
        String[] splitstring = sentence.split("\s");
        int length = 0;
        List<String> res = new ArrayList<>();
        for (String word : splitstring) {
            if (word.length() == length) {
                res.add(word);
            } else if (word.length() > length) {
                res.clear();
                res.add(word);
                length = word.length();
            }
        }
        java.util.Collections.sort(res);
        return res.get(0);
    }

    /**
     * Classic count the words exercise.
     * <p>
     * From input count all the words and collect results to map.
     *
     * @param sentence array of strings, can't be null.
     * @return map containing all word to count mappings.
     */
    public static Map<String, Integer> wordCount(String[] sentence) {
        HashMap<String, Integer> res = new HashMap<>();
        if (sentence == null) {
            return res;
        }
        for (String word : sentence) {
            if (!res.containsKey(word)) {
                int amount = Collections.frequency(List.of(sentence), word);
                res.put(word, amount);
            }
        }
        return res;
    }

    /**
     * Loop over the given list of strings to build a resulting list of string like this:
     * when a string appears the 2nd, 4th, 6th, etc. time in the list, append the string to the result.
     * <p>
     * Return the empty list if no string appears a 2nd time.
     * <p>
     * Use map to count times that the string has appeared.
     *
     * @param words input list to filter
     * @return list of strings matching criteria
     */
    public static List<String> onlyEvenWords(List<String> words) {
        HashMap<String, Integer> append = new HashMap<>();
        List<String> res = new ArrayList<>();
        for (String word : words) {
            int amount = Collections.frequency(words, word);
            if (!append.containsKey(word)) {
                append.put(word, amount);
            }
        }
        for (Map.Entry<String, Integer> entry : append.entrySet()) {
            int count = entry.getValue();
            for (int i = 0; i < count / 2; i++) {
                res.add(entry.getKey());
            }
        }
        return res;
    }

    /**
     * Method to save student and student's grade(you should use a Map here).
     * Only add student if his/hers grade is in the range of 0-5.
     *
     * @param studentInfo String with a pattern (name:grade)
     */
    public void addStudent(String studentInfo) {
        String[] student = studentInfo.split(":");
        int number = Integer.parseInt(student[1]);
        if (number >= 0 && number <= 5) {
            studentInfot.put(student[0], number);
        }

    }

    /**
     * Method to get student's grade.
     * Return the student's grade by his/hers name.
     * You can assume that the user is already added(previous function with student's info already called).
     *
     * @param name String students
     * @return int student's grade.
     */
    public int getStudentGrade(String name) {
        if (studentInfot.containsKey(name)) {
            return studentInfot.get(name);
        }
        return -1;
    }
}
