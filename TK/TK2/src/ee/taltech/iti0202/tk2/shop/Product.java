package ee.taltech.iti0202.tk2.shop;

public class Product {

    private String nameP;
    private Integer priceP;

    /***
     * Cons.
     */
    public Product(String name, Integer price) {
        nameP = name;
        priceP = price;
    }

    public Product(Integer price) {
        priceP = price;
    }

    public String getName() {
        return nameP;
    }

    public Integer getPrice() {
        return priceP;
    }

    @Override
    public String toString() {
        return nameP + " " + priceP;
    }
}
