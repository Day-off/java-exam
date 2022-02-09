package ee.taltech.iti0202.bookshelf;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {
    private String personName;
    private int personMoney;
    private List<Book> books = new ArrayList<Book>();
    private boolean buying = false;

    public Person(String name, int money) {
        personMoney = money;
        personName = name;
    }

    public int getMoney() {

        return personMoney;
    }

    public String getName() {

        return personName;
    }

    public void removeBook(Book book){
        books.remove(book);
    }

    public void addBook(Book book){
        books.add(book);
    }

    public boolean buyBook(Book book) {
        if (personMoney >= book.getPrice() && !(books.contains(book))) {
                personMoney -= book.getPrice();
                addBook(book);
                buying = true;
                return true;
        }
        return false;
    }

    public boolean sellBook(Book book) {
        if (books.contains(book)){
            personMoney += book.getPrice();
            removeBook(book);
            return true;
        }
        return false;
    }

    public List<Book> getBooks(){
        return books;
    }
    public boolean getBuyingState(){
        return buying;
    }
}