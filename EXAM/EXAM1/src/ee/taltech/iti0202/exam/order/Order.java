package ee.taltech.iti0202.exam.order;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private static int globalid = 1;
    private List<Product> productsInOrder = new ArrayList<>();
    private int sum = 0;


    public Order() {
        id = setId();
        setnextId();
    }

    public static void setnextId() {
        globalid += 1;
    }

    public int setId() {
        id = globalid;
        return id;
    }

    public int getId() {
        return id;
    }

    public void addProducts(Product product) {
        if (!productsInOrder.contains(product)) {
            productsInOrder.add(product);
            sum += product.getPrice();
        }
    }

    public List<Product> getProductsInOrder() {
        return productsInOrder;
    }

    public int getSum() {
        return sum;
    }

    public void cleanOrder() {
        sum = 0;
        productsInOrder.clear();
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", productsInOrder=" + productsInOrder +
                ", sum=" + sum +
                '}';
    }
}
