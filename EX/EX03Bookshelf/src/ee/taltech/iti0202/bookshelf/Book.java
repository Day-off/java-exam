package ee.taltech.iti0202.bookshelf;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Book {
    private final String bName;
    private final String bAuthor;
    Person owner;
    static Book cop;
    private Integer byearOfPublishing = 0;
    private final Integer bprice;
    private Integer bId = 0;
    private static Integer nextbId = -1;
    private static final List<Book> listofBooks = new ArrayList<>();
    private static final List<Book> personbooks = new ArrayList<>();
    private static final List<Book> authorbooks = new ArrayList<>();

    /***
     * index counter
     */
    public static int getAndIncrementNextId() {
        return nextbId += 1;
    }

    /***
     *Book Constructor
     */
    public Book(String title, String author, int yearOfPublishing, int price) {
        bName = title;
        bAuthor = author;
        byearOfPublishing = yearOfPublishing;
        bprice = price;
        bId = getAndIncrementNextId();
    }

    /***
     * Title getter
     */
    public String getTitle() {

        return this.bName;
    }

    /***
     * Author getter
     */
    public String getAuthor() {

        return bAuthor;
    }

    /***
     * year getter
     */
    public int getYearOfPublishing() {
        return this.byearOfPublishing;
    }

    /***
     * owner getter
     */
    public Person getOwner() {
        return owner;
    }

    /***
     * price getter
     */
    public int getPrice() {
        return bprice;
    }

    /***
     * Id getter
     */
    public int getId() {
        return bId;
    }

    /***
     * Owner setter
     */
    public void setOwner(Person newOwner) {
        this.owner = newOwner;
    }

    /***
     * Check person can buy book
     */
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

    public static Book of(String title, String author, int yearOfPublishing, int price) {
        for (Book bookinlist : listofBooks) {
            if (bookinlist.getPrice() == price && Objects.equals(bookinlist.getAuthor(), author) && Objects.equals(bookinlist.getTitle(), title) && bookinlist.getYearOfPublishing() == yearOfPublishing) {
                return bookinlist;
            }
        }
        cop = new Book(title, author, yearOfPublishing, price);
        listofBooks.add(cop);
        return cop;

    }

    public static Book of(String title, int price) {
        if (listofBooks.size() == 0) {
            return null;
        } else if (listofBooks.get(listofBooks.size() - 1).getPrice() != price && !Objects.equals(listofBooks.get(listofBooks.size() - 1).getTitle(), title)) {
            cop = new Book(title, listofBooks.get(listofBooks.size() - 1).getAuthor(), listofBooks.get(listofBooks.size() - 1).getYearOfPublishing(), price);
            listofBooks.add(cop);
            return cop;
        }
        return listofBooks.get(listofBooks.size() - 1);
    }

    public static List<Book> getBooksByOwner(Person owner) {
        for (Book bookinlist : listofBooks) {
            if (bookinlist.getOwner() == owner) {
                personbooks.add(bookinlist);
            }
        }
        return personbooks;
    }

    public static boolean removeBook(Book book) {
        if (book == null) {
            return false;
        }
        for (Book bookinlist : listofBooks) {
            if (bookinlist == book) {
                if (book.getOwner() != null) {
                    bookinlist.getOwner().sellBook(book);
                }
                personbooks.remove(bookinlist);
                listofBooks.remove(book);
                authorbooks.remove(book);
                return true;
            }
        }return false;
    }

    public static List<Book> getBooksByAuthor(String author) {
        for (Book bookinlist : listofBooks) {
            if (Objects.equals(bookinlist.getAuthor().toLowerCase(Locale.ROOT), author.toLowerCase(Locale.ROOT))) {
                authorbooks.add(bookinlist);
            }
        }
        return authorbooks;
    }

}