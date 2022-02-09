package ee.taltech.iti0202.bookshelf;

import java.util.Objects;

public class Book {
    private String bName, bAuthor;
    Person owner;
    private Integer byearOfPublishing, bprice, bId;

    public static int getAndIncrementNextId() {
        return 0;
    }

    public Book(String title, String author, int yearOfPublishing, int price) {
        bName = title;
        bAuthor = author;
        byearOfPublishing = yearOfPublishing;
        bprice = price;
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

}