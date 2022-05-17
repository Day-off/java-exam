package ee.taltech.iti0202.exam;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;



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
        String[] numbers = input.split(",");
        HashMap<Integer, Integer> res = new HashMap<>();
        int count = 1;
        for (String s : numbers) {
            if (!res.containsKey(Integer.parseInt(s))){
                for (String n : numbers){
                    if (n.equals(s)){
                        count += 1;
                    }
                }res.put(Integer.parseInt(s), count);
                count = 0;
            }

        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(frequencyBasedSort("1,2,1,3"));  // 1, 1, 3, 2
    }

}
