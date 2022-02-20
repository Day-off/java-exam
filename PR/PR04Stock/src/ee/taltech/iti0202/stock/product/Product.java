package ee.taltech.iti0202.stock.product;
import ee.taltech.iti0202.stock.exceptions.StockException;

public class Product {

    private final String nameProduct;
    private final int priceProduct;
    private static int nextId = 0;
    private final int Id;

    /**
     * Create a new product with the given name and price.
     * <p>
     * If the price is negative, throw a StockException with a NEGATIVE_PRICE reason.
     *
     * @param name Name of the product.
     * @param price Price of the product.
     * @throws StockException NEGATIVE_PRICE
     */
    public Product(String name, int price) throws StockException {
        if (price < 0){
            throw new StockException(StockException.Reason.NEGATIVE_PRICE);
        }
        nameProduct = name;
        priceProduct = price;
        Id = getNextId();
    }

    /**
     * Returns the next id.
     *
     * This value has to increment with every call.
     *
     * @return The next id.
     */
    public static int getNextId() {
        nextId += 1;
        return nextId;
    }

    /**
     * Returns id of the product.
     * 
     * @return id of the product.
     */
    public int getId() {
        return Id;
    }

    /**
     * Returns the name of the product.
     * 
     * @return the name of the product.
     */
    public String getName() {
        return nameProduct;
    }

    /**
     * Returns the price of the product.
     * 
     * @return the price of the product.
     */
    public int getPrice() {
        return priceProduct;
    }

    @Override
    public String toString(){
        return nameProduct + " " + "pr: " + priceProduct + " Id: " + Id;
    }

}