package ee.taltech.iti0202.bookshelf;

import java.util.List;
import java.util.Objects;

public class Book {
    private final String bName;
    private final String bAuthor;
    Person owner;
    private Integer byearOfPublishing = 0;
    private final Integer bprice;
    private Integer bId = 0;
    private static Integer nextbId = 0;


    public static int getAndIncrementNextId() {
        nextbId += 1;
        return nextbId + 1;
    }

//    public void createNextId() {
//        nextbId++;
//    }

    public Book(String title, String author, int yearOfPublishing, int price) {
        bName = title;
        bAuthor = author;
        byearOfPublishing = yearOfPublishing;
        bprice = price;
        bId = nextbId;
        getAndIncrementNextId();
    }

    public String getTitle() {

        return this.bName;
    }

    public String getAuthor() {

        return bAuthor;
    }

    public int getYearOfPublishing() {
        return this.byearOfPublishing;
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
        this.owner = newOwner;
    }

    public boolean buy(Person buyer) {
        if (this.owner == null) {
            if (buyer != null) {
                return buyer.buyBook(this);
            }
            return false;
        } else {
            if (buyer != null) {
                return buyer.buyBook(this);
            }
            return this.owner.sellBook(this);
        }
    }


}