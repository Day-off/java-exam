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

    /***
     * op
     * @param price
     */
    public Product(Integer price) {
        priceP = price;
    }

    /***
     * op
     */
    public String getName() {
        return nameP;
    }

    /***
     * op
     */
    public Integer getPrice() {
        return priceP;
    }

    /***
     * op
     */
    @Override
    public String toString() {
        return nameP + " " + priceP;
    }
}
