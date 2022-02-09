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
        nextbId ++;
        return nextbId + 1;
    }

    public void createNextId() {
        nextbId++;
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
        return buyer.buyBook(new Book(bName, bAuthor, byearOfPublishing, bprice));
    }



}