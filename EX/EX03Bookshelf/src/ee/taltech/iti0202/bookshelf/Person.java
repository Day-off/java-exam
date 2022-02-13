package ee.taltech.iti0202.bookshelf;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private final String personName;
    private int personMoney;
    private final List<Book> books = new ArrayList<>();

    /***
     * constructor
     */
    public Person(String name, int money) {
        this.personMoney = money;
        this.personName = name;
    }

    /***
     * getter
     */
    public int getMoney() {

        return this.personMoney;
    }

    /***
     * getter
     */
    public String getName() {

        return this.personName;
    }

    /***
     * getter
     */
    public void removeMoney(Integer money) {
        personMoney -= money;
    }

    /***
     * getter
     */
    public void addMoney(Integer money) {
        personMoney += money;
    }

    /***
     * del book
     */
    public void removeBook(Book book) {
        books.remove(book);
    }

    /***
     * add book
     */
    public void addBook(Book book) {
        books.add(book);
    }

    /***
     * buy book
     */
    public boolean buyBook(Book book) {
        if (book == null) {
            return false;
        }
        if (personMoney >= book.getPrice() && (book.getOwner() != this)) {
            removeMoney(book.getPrice());
            if (book.getOwner() != null) {
                book.getOwner().addMoney(book.getPrice());
            }
            addBook(book);
            book.setOwner(this);
            return true;
        }
        return false;
    }


    /***
     * sell book
     */
    public boolean sellBook(Book book) {
        if (book != null) {
            if (books.contains(book)) {
                addMoney(book.getPrice());
                removeBook(book);
                book.setOwner(null);
                return true;
            }
        }
        return false;
    }

    /***
     * getter
     */
    public List<Book> getBooks() {

        return books;
    }
}
