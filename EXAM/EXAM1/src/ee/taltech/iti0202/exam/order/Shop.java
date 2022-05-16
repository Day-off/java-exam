package ee.taltech.iti0202.exam.order;

import java.util.*;

public class Shop {

    private final List<Product> products = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();

    public Shop() {
    }

    public boolean addProduct(Product product) {
        if (!products.contains(product)) {
            products.add(product);
            return true;
        }
        return false;
    }

    public int createNewOrder() {
        orders.add(new Order());
        return orders.get(orders.size() - 1).getId();
    }

    public boolean addProductToOrder(int orderNumber, String itemName) {
        Optional<Order> order = orders.stream().filter(ord -> ord.getId() == orderNumber).findFirst();
        List<Product> prod = products.stream().filter(product -> product.getName().equals(itemName)).toList();
        if (order.isEmpty() || prod.size() == 0 || order.get().isCanseld()) {
            return false;
        } else {
            Product p = Collections.min(prod, Comparator.comparing(Product::getPrice));
            order.get().addProducts(p);
            products.remove(p);
            return true;
        }
    }

    public int getOrderSum(int orderNumber) {
        Optional<Order> order = orders.stream().filter(ord -> ord.getId() == orderNumber).findFirst();
        if (order.isEmpty()) {
            return -1;
        }
        if (order.get().isCanseld()) {
            return 0;
        }
        return order.get().getSum();
    }

    public boolean cancelOrder(int orderNumber) {
        Optional<Order> order = orders.stream().filter(ord -> ord.getId() == orderNumber).findFirst();
        if (order.isEmpty() || order.get().isCanseld()) {
            return false;
        }
        products.addAll(order.get().getProductsInOrder());
        order.get().cleanOrder();
        return true;
    }

    public List<Product> getAvailableProducts() {
        return products;
    }

}
