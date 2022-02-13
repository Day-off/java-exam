package ee.taltech.iti0202.bookshelf;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class Book {

    private final String bName;
    private final String bAuthor;
    private static String lastAuthor;
    private static Integer lastYear;
    Person owner;
    static Book cop;
    private Integer byearOfPublishing = 0;
    private final Integer bprice;
    private Integer bId = 0;
    private static Integer nextbId = -1;
    private static final List<Book> listbooks = new ArrayList<>();
    private static final HashMap<String, List<Book>> authordict = new HashMap<>();


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

    /***
     * create book
     */
    public static Book of(String title, String author, int yearOfPublishing, int price) {
        if (authordict.containsKey(author.toLowerCase(Locale.ROOT))) {
            for (Book object : authordict.get(author.toLowerCase(Locale.ROOT))) {
                if (Objects.equals(object.getTitle(), title) && object.getYearOfPublishing() == yearOfPublishing) {
                    return object;
                }
            }
        }
        cop = new Book(title, author, yearOfPublishing, price);
        listbooks.add(cop);
        lastAuthor = cop.getAuthor();
        lastYear = cop.getYearOfPublishing();
        sortByAuthor(cop);
        return cop;
    }

    /***
     *create book without author and year
     */
    public static Book of(String title, int price) {
        if (listbooks.size() == 0) {
            return null;
        } else if (!Objects.equals(listbooks.get(listbooks.size() - 1).getTitle(), title) &&
                listbooks.get(listbooks.size() - 1).getPrice() != price) {
            cop = new Book(title, lastAuthor, lastYear, price);
            listbooks.add(cop);
            sortByAuthor(cop);
            return cop;
        } else {
            return listbooks.get(listbooks.size() - 1);
        }

    }


    /***
     *person books
     */
    public static List<Book> getBooksByOwner(Person owner) {
        return owner.getBooks();
    }

    /***
     * sorting
     */
    public static void sortByAuthor(Book object) {
        List<Book> n = new ArrayList<>();
        if (authordict.containsKey(object.getAuthor().toLowerCase(Locale.ROOT))) {
            n = authordict.get(object.getAuthor().toLowerCase(Locale.ROOT));
        }
        n.add(object);
        authordict.put(object.getAuthor().toLowerCase(Locale.ROOT), n);

    }

    /***
     * getter
     */
    public static List<Book> getBooksByAuthor(String author) {
        if (authordict.containsKey(author.toLowerCase(Locale.ROOT))) {
            return authordict.get(author.toLowerCase(Locale.ROOT));
        } else {
            return new ArrayList<Book>();
        }
    }

    /***
     *delet book
     */
    public static boolean removeBook(Book book) {
        if (book == null || !listbooks.contains(book)) {
            return false;
        }
        if (book.getOwner() != null) {
            book.getOwner().addMoney(book.getPrice());
            book.getOwner().removeBook(book);
        }
        listbooks.remove(book);
        if (authordict.containsKey(book.getAuthor().toLowerCase(Locale.ROOT))) {
            authordict.get(book.getAuthor().toLowerCase(Locale.ROOT)).remove(book);
        }

        return true;
    }

}
