package ee.taltech.iti0202.bookshelf;

import java.util.*;

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
    private static final List<Book> listBooks = new ArrayList<>();

    private static final HashMap<String, List<Book>> authorDict = new HashMap<>();


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
        for (Book object : listBooks) {
            if (Objects.equals(object.getTitle(), title) && Objects.equals(object.getAuthor(), author) && object.getYearOfPublishing() == yearOfPublishing) {
                return object;
            }
        }
        cop = new Book(title, author, yearOfPublishing, price);
        listBooks.add(cop);
        lastAuthor = cop.getAuthor();
        lastYear = cop.getYearOfPublishing();
        sortByAuthor(cop);
        return cop;
    }

    /***
     *
     */
    public static Book of(String title, int price) {
        if (listBooks.size() == 0) {
            return null;
        } else if (!Objects.equals(listBooks.get(listBooks.size() - 1).getTitle(), title) && listBooks.get(listBooks.size() - 1).getPrice() != price) {
            cop = new Book(title, lastAuthor, lastYear, price);
            listBooks.add(cop);
            sortByAuthor(cop);
            return cop;
        } else {
            return listBooks.get(listBooks.size() - 1);
        }

    }


    /***
     *
     */
    public static List<Book> getBooksByOwner(Person owner) {
        return owner.getBooks();
    }

    public static void sortByAuthor(Book object) {
        List<Book> n = new ArrayList<>();
        if (authorDict.containsKey(object.getAuthor().toLowerCase(Locale.ROOT))) {
            n = authorDict.get(object.getAuthor().toLowerCase(Locale.ROOT));
        }
        n.add(object);
        authorDict.put(object.getAuthor().toLowerCase(Locale.ROOT), n);

    }

    public static List<Book> getBooksByAuthor(String author) {
        return authorDict.get(author.toLowerCase(Locale.ROOT));
    }

    /***
     *
     */
    public static boolean removeBook(Book book) {
        if (book == null || !listBooks.contains(book)) {
            return false;
        }
        if (book.getOwner() != null){
            book.getOwner().addMoney(book.getPrice());
            book.getOwner().removeBook(book);
        }
        listBooks.remove(book);
        authorDict.get(book.getAuthor()).remove(book);
        return true;
    }


}