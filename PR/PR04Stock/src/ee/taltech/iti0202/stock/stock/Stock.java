package ee.taltech.iti0202.stock.stock;

import ee.taltech.iti0202.stock.exceptions.StockException;
import ee.taltech.iti0202.stock.product.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * The stock class.
 * <p>
 * Each stock has a name, list of products that are currently stored in it
 * and the maximum amount of products that stock can store.
 * <p>
 * If adding a product to the stock is not possible, due to exceeding the maximum limit of products
 * OR stock already contains a product, a StockException must be thrown,
 * otherwise product must be added to the stock.
 * <p>
 * When getting (not removing) a product from our stock,
 * it is important to find the product with the lowest price first.
 */

public class Stock {

    private final int stockCapacity;
    private final List<Product> stock = new ArrayList<>();
    private List<Product> sortedProducts;


    /**
     * Create a new stock with the given name and the max capacity for the products.
     *
     * @param name        the name of the stock.
     * @param maxCapacity max amount of products allowed in the stock.
     */
    public Stock(String name, int maxCapacity) {
        stockCapacity = maxCapacity;

    }

    /**
     * Add a product to the stock, if stock does not contain the product and is not full yet.
     * <p>
     * Check in following order:
     * If stock already contains a product, throw an StockException with a STOCK_ALREADY_CONTAINS_PRODUCT reason.
     * If stock is full, throw a StockException with a STOCK_IS_FULL reason.
     *
     * @param product to be added
     * @throws StockException STOCK_ALREADY_CONTAINS_PRODUCT, STOCK_IS_FULL
     */

    public void addProduct(Product product) throws StockException {
        if (stock.contains(product)) {
            throw new StockException(StockException.Reason.STOCK_ALREADY_CONTAINS_PRODUCT);
        } else if (stock.size() == stockCapacity) {
            throw new StockException(StockException.Reason.STOCK_IS_FULL);
        } else {
            stock.add(product);
        }
    }

    /***
     * find product
     * @return Optional
     */
    public Optional<Product> findCheaperProduct(String name) {
        List<Product> sameProducts = stock.stream().filter(object -> object.getName().equals(name)).toList();
        if (sameProducts.size() != 0) {
            // Sort the stream:
            sortedProducts = new ArrayList<>();
            sortedProducts = sameProducts.stream().sorted(Comparator
                            .comparing(Product::getPrice)
                            .thenComparing(Product::getId))
                    .collect(Collectors.toList());
            return Optional.of(sortedProducts.get(0));

        } else {
            sortedProducts = new ArrayList<>();
            return Optional.empty();
        }
    }

    /**
     * Get a product from a stock by name with the lowest price.
     * <p>
     * If there are several products with the same name and price,
     * returns the product with the lowest id.
     *
     * @param name the product's name
     */
    public Optional<Product> getProduct(String name) {
        return findCheaperProduct(name);
    }

    /**
     * Remove and return a product from a stock,
     * if stock has a given product.
     * <p>
     * Use getProduct() method to get the product.
     * <p>
     * If there is nothing to remove, return Optional.empty()
     *
     * @param name Name of the product to be removed
     * @return Optional
     */

    public Optional<Product> removeProduct(String name) {
        Optional<Product> product = findCheaperProduct(name);
        if (product.isEmpty()) {
            return Optional.empty();
        } else {
            stock.remove(sortedProducts.get(0));
            return product;
        }
    }

    /**
     * Get a list of current products in the stock.
     *
     * @return List
     */
    public List<Product> getProducts() {
        return stock;
    }

    /**
     * Get a list of current products in the stock filtered by name.
     * <p>
     * Order the products by price ascending. In case of the same price, by id ascending.
     *
     * @param name Name to be filtered.
     * @return List
     */
    public List<Product> getProducts(String name) {
        findCheaperProduct(name);
        return sortedProducts;
    }

    /**
     * Get total price of all the products.
     *
     * @return Total price.
     */
    public int getTotalPrice() {
        int totalPrice = 0;
        for (Product product : stock) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    /**
     * Check if stock is full.
     *
     * @return boolean
     */
    public boolean isFull() {
        return stock.size() == stockCapacity;
    }


    public static void main(String[] args) throws StockException {
        Product manka = new Product("manka", 1);//1
        Product puding = new Product("puding", 1);//2
        Product zele = new Product("zele", 3);//3

        Product q = new Product("sok", 1); //4
        Product w = new Product("sok", 2);//5
        Product e = new Product("sok", 2);//6
        Product r = new Product("sok", 2);//7
        Product t = new Product("sok", 2);//8
        Product y = new Product("sok", 1);//9


        Stock stock = new Stock("Holodilnik", 10);

        stock.addProduct(manka);
        stock.addProduct(puding);
        stock.addProduct(zele);
        stock.addProduct(q);
        stock.addProduct(w);
        System.out.println(stock.getProducts().size());//2

        stock.addProduct(e);
        stock.addProduct(r);
        stock.addProduct(t);
        stock.addProduct(y);

        System.out.println(stock.getProducts().size());//5

        stock.getProducts("sok").forEach(s -> System.out.println(s.getId() + " " + s.getPrice()));

        System.out.println(stock.removeProduct("sok"));
        System.out.println(stock.removeProduct("sok"));
        System.out.println(stock.removeProduct("sok"));
        System.out.println(stock.removeProduct("sok"));
        System.out.println(stock.removeProduct("sok"));
//        System.out.println(stock.removeProduct("sok"));


        stock.getProducts().forEach(s -> System.out.println(s.getId() + " " + s.getName()));//4


        System.out.println(stock.getProducts("sok"));


    }

}
