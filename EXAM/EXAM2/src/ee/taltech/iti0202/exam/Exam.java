package ee.taltech.iti0202.exam;

import java.util.*;
import java.util.stream.Collectors;


public class Exam {
    /**
     * Return a list that contains the exact same numbers as the given list,
     * but rearranged so that all the zeros are grouped at the start of the list.
     * <p>
     * The order of the non-zero numbers does not matter.
     * So [1, 0, 0, 1] becomes [0 ,0, 1, 1].
     * You may modify and return the given list or make a new list.
     * <p>
     * zeroFront([1, 0, 0, 1]) => [0, 0, 1, 1]
     * zeroFront([0, 1, 1, 0, 1]) => [0, 0, 1, 1, 1]
     * zeroFront([1, 0]) => [0, 1]
     *
     * @param numbers list of integers
     * @return "ordered" list
     */
    public static List<Integer> zeroFront(List<Integer> numbers) {
        ArrayList<Integer> zero = new ArrayList<>();
        ArrayList<Integer> others = new ArrayList<>();
        for (int num : numbers) {
            if (num == 0) {
                zero.add(num);
            } else {
                others.add(num);
            }
        }
        zero.addAll(others);
        return zero;
    }

    /**
     * You are given a string as an input where which represents a sequence of numbers in the format `num, num, num`
     * Make a function frequencyBasedSort that returns a list where the most popular numbers of the input
     * are at the front and the least popular numbers are at the back of the list.
     * If two numbers are equally popular then the bigger number must come first.
     * <p>
     * Examples:
     * frequencyBasedSort("3,1") => {3, 1}
     * frequencyBasedSort("3,3,2,4,5,1,5") => {5, 5, 3, 3, 4, 2, 1}
     * frequencyBasedSort("1,2,3,4,5,1,2,2,3,3,1") => {3, 3, 3, 2, 2, 2, 1, 1, 1, 5, 4}
     *
     * @param input the sequence of numbers as a string, separate the numbers by coma and leave no empty spaces
     * @return a list that is sorted by to number popularity
     */
    public static List<Integer> frequencyBasedSort(String input) {
        String[] nums = input.split(",");
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        List<String> control = new ArrayList<>();
        for (String s : nums) {
            if (!control.contains(s)) {
                control.add(s);
                List<Integer> colect = new ArrayList<>();
                for (String n : nums) {
                    if (s.equals(n)) {
                        colect.add(Integer.parseInt(n));
                    }
                }
                if (!map.containsKey(colect.size())) {
                    map.put(colect.size(), new ArrayList<>(colect));
                } else {
                    List<Integer> i = map.get(colect.size());
                    i.addAll(colect);
                    map.put(colect.size(), i);
                }colect.clear();
            }
        }
        Set<Integer> keys = map.keySet();
        List<Integer> res = new ArrayList<>();
        for (int i = keys.size() - 1; i > 0; i-- ){
            res.addAll(map.get(i).stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()));
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(frequencyBasedSort("1,2,1,3,4,4,4"));  // 1, 1, 3, 2
    }

}
