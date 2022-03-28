package ee.taltech.iti0202.kt.mail;

import java.util.ArrayList;
import java.util.List;

public class PostOffice {

    private final String location;
    private final List<Letter> letters = new ArrayList<>();
    private final List<Postman> postmans = new ArrayList<>();
    private static final int MAX_AGE = 40;


    /**
     * Create a post office with the location.
     */
    public PostOffice(String location) {
        this.location = location;
    }

    /**
     * Adds a letter to the post office.
     * Letter is added only if the letter's destination city matches the location of the office.
     */
    public void addLetter(Letter letter) {
        if (this.location.equals(letter.getDestinationCity())) {
            letters.add(letter);
        }
    }

    /**
     * Adds a postman to the office.
     * If there is a postman with the same first letter already in the office,
     * then this postman is not added.
     * Otherwise postman is added to the office.
     */
    public void addPostman(Postman postman) {
        int count = 0;
        for (Postman p : this.postmans) {
            if (p.getName().charAt(0) == postman.getName().charAt(0)) {
                count += 1;
                break;
            }
        }
        if (count == 0) {
            postmans.add(postman);
        }

    }

    /**
     * Returns all the letter in the post office.
     */
    public List<Letter> getAllLetters() {
        return this.letters;
    }

    /**
     * Returns all the postmen in the office.
     */
    public List<Postman> getPostmen() {
        return this.postmans;
    }

    /**
     * Divide letter in the office to postmen.
     * The division algorithm is as follows:
     * Each letter is assigned to each postman.
     * If the postman can take this letter (addLetter method), then this letter
     * is added to the postman and removed from the office.
     * If no postman can take the letter, then this letter remains in the office.
     */
    public void divideLetters() {
        List<Letter> copyL = List.copyOf(this.letters);
        for (Letter l : copyL) {
            for (Postman p : postmans) {
                if (p.addLetter(l)) {
                    this.letters.remove(l);
                }
            }
        }
    }

    public static void main(String[] args) {
        PostOffice postOffice = new PostOffice("Tallinn");

        Postman postman = new Postman("Martin", MAX_AGE);

        postOffice.addPostman(postman);

        postOffice.addLetter(new Letter("Toomas", "Tartu", "Rahu tn"));
        postOffice.addLetter(new Letter("Erki", "Tallinn", "Männi tee"));

        postOffice.divideLetters();

        System.out.println(postman.getLetters());   // [City: Tallinn, Address: Männi tee, Recipient: Erki]
    }
}
