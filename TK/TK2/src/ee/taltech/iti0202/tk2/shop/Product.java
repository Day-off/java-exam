package ee.taltech.iti0202.tk2.shop;

public class Product {

    private String nameP;
    private int priceP;

    /***
     * Cons.
     */
    public Product(String name, int price) {
        nameP = name;
        priceP = price;
    }

    /***
     * op
     * @param price
     */
    public Product(int price) {
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
        if (this.getName() == null) {
            return "(" + priceP + ")";
        }
        return nameP + " " + "(" + priceP + ")";
    }
}
