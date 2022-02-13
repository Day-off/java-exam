package ee.taltech.iti0202.bookshelf;

public class Main {

    private static final  Integer M1 = 1926;
    private static final Integer P1 = 100;
    private final static Integer M2 = 1976;
    private final static Integer P2 = 200;
    private final static Integer BP1 = 200;
    private final static Integer BP2 = 300;


    public static void main(String[] args) {
        Book tammsaare = new Book("Truth and Justice", "Tammsaare", M1, P1);
        Book meri = new Book("Silverwhite", "Meri", M2, P2);

        Person mati = new Person("Mati", BP1);
        Person kati = new Person("Kati", BP2);

        System.out.println(mati.buyBook(tammsaare)); // true
        System.out.println(mati.getMoney());  // 100
        System.out.println(tammsaare.getOwner().getName());
        System.out.println(tammsaare.getPrice());// 100

        System.out.print("\n");

        System.out.println(mati.sellBook(tammsaare)); // true
        System.out.println(mati.getMoney()); // 200
        System.out.println(tammsaare.getOwner()); // null

        System.out.print("\n");

        System.out.println(mati.sellBook(null)); // false

        System.out.println("\n");

        System.out.println(mati.buyBook(null)); // true
        System.out.println(mati.getMoney()); // 0

        System.out.println("\n");

        System.out.println(meri.buy(kati)); // true
        System.out.println(mati.getMoney()); // 200`
        System.out.println(kati.getMoney()); // 100
        System.out.println(meri.buy(kati)); // false
        System.out.println(kati.getMoney()); // 100
        System.out.println(meri.getOwner().getName()); // Kati
        System.out.println(kati.sellBook(meri)); // true
        System.out.println(meri.getOwner()); // null

        System.out.println("\n");

        // id
        System.out.println(tammsaare.getId()); // 0
        System.out.println(meri.getId()); // 1
        System.out.println(Book.getAndIncrementNextId()); // 2

    }
}