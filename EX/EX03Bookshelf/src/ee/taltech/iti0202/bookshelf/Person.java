package ee.taltech.iti0202.bookshelf;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {
    private final String personName;
    private int personMoney;
    private final List<Book> books = new ArrayList<Book>();

    public Person(String name, int money) {
        this.personMoney = money;
        this.personName = name;
    }

    public int getMoney() {

        return this.personMoney;
    }

    public String getName() {

        return this.personName;
    }

    public void removeMoney(Integer money) {
        this.personMoney -= money;
    }

    public void addMoney(Integer money) {
        this.personMoney += money;
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public void addBook(Book book) {
        books.add(book);
    }

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
        }return false;
    }

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

    public List<Book> getBooks() {
        return books;
    }
}