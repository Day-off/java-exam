package ee.taltech.iti0202.bookshelf;

import java.util.List;
import java.util.Objects;

public class Book {
    private final String bName;
    private final String bAuthor;
    Person owner;
    private final Integer byearOfPublishing ;
    private final Integer bprice;
    private final Integer bId;
    private static Integer nextbId = 0;


    public static int getAndIncrementNextId() {
        return nextbId;
    }

    public void createNextId() {
        nextbId++;
    }

    public Book(String title, String author, int yearOfPublishing, int price) {
        this.bName = title;
        this.bAuthor = author;
        this.byearOfPublishing = yearOfPublishing;
        this.bprice = price;
        this.bId = nextbId;
        createNextId();
    }

    public String getTitle() {

        return this.bName;
    }

    public String getAuthor() {

        return this.bAuthor;
    }

    public int getYearOfPublishing() {
        return this.byearOfPublishing;
    }

    public Person getOwner() {
        return this.owner;
    }

    public int getPrice() {
        return this.bprice;
    }

    public int getId() {
        return this.bId;
    }

    public void setOwner(Person newOwner) {
        this.owner = newOwner;
    }

    public boolean buy(Person buyer) {
        return (this.owner != buyer)&& (buyer.getMoney() >= this.bprice) && this.owner.sellBook(this) && buyer.buyBook(this);
    }


}