package ee.taltech.iti0202.kt.mail;

import java.util.ArrayList;
import java.util.List;

public class Postman {

    private final String name;
    private final int age;
    private final List<Letter> letters = new ArrayList<>();
    private int limit;

    /**
     * Create a postman with the name and the age.
     */
    public Postman(String name, Integer age) {
        this.name = name;
        this.age = age;
        getLimit();
    }

    public String getName() {
        return this.name;
    }

    public List<Letter> getLetters() {
        return this.letters;
    }

    public void getLimit() {
        if (this.age >= 40) {
            this.limit = this.age - name.length();
        } else {
            this.limit = this.age + name.length();
        }
    }

    /**
     * Adds a letter to postman.
     * The letter can be added if the name of the postman and the name of the letter's address
     * start with the same symbol.
     * Also, each postman has a letter limit.
     * If the age of the postman is 40 or larger, then the limit of the letters is: age - name length
     * If the age of the postman is below 40, the limit is age + name length.
     * If the first letters do not match or the letter limit is reached, returns false.
     * Otherwise, returns true and letter is added to postman.
     */
    public boolean addLetter(Letter letter) {
        if (this.name.charAt(0) != letter.getAddress().charAt(0) || (this.letters.size() >= this.limit)) {
            return false;
        }
        letters.add(letter);
        return true;
    }
}
