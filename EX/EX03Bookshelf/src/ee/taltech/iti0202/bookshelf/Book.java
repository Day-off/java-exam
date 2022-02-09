package ee.taltech.iti0202.bookshelf;

import java.util.List;
import java.util.Objects;

public class Book {
    private String bName = "", bAuthor = "";
    Person owner;
    private Integer byearOfPublishing = 0;
    private Integer bprice = 0, bId = 0;
    private static Integer nextbId = 0;


    public static int getAndIncrementNextId() {
        return nextbId;
    }

    public void createNextId(){
        nextbId ++;
    }

    public Book(String title, String author, int yearOfPublishing, int price) {
        bName = title;
        bAuthor = author;
        byearOfPublishing = yearOfPublishing;
        bprice = price;
        bId = nextbId;
        createNextId();
    }

    public String getTitle() {

        return bName;
    }

    public String getAuthor() {

        return bAuthor;
    }

    public int getYearOfPublishing() {
        return byearOfPublishing;
    }

    public Person getOwner() {
        return owner;
    }

    public int getPrice() {
        return bprice;
    }

    public int getId() {
        return bId;
    }

    public void setOwner(Person newOwner) {
        owner = newOwner;
    }

    public boolean buy(Person buyer) {
        if (buyer.getBuyingState()) {
            setOwner(buyer);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        // second part
        Book b0 = new Book("Java EX00\n", "ron\n", 1, 2);
        Book b1 = new Book("Java EX01\n", "Ago Luberg\n", 2018, 3);
        Book b2 = new Book("Java EX02\n", "som\n", 1, 2);

        System.out.print(b0.getAuthor());
        System.out.print(b1.getAuthor());
        System.out.print(b2.getAuthor());
        System.out.print(b0.getTitle());
        System.out.print(b1.getTitle());
        System.out.print(b2.getTitle());
    }


}