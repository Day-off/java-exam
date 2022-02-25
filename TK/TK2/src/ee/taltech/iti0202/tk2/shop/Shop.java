package ee.taltech.iti0202.tk2.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Shop {

    private List<Product> korv = new ArrayList<>();

    /***
     * con
     */
    public Shop() {

    }

    /***
     * con
     * @param product
     * @return
     */
    public boolean addProduct(Product product) {
        if (product.getPrice() < 0) {
            return false;
        }
        for (Product ob : korv) {
            if (product.getName() == null) {
                if (Objects.equals(ob.getPrice(), product.getPrice())) {
                    return false;
                }
            } else if (Objects.equals(ob.getPrice(), product.getPrice())
                    && Objects.equals(ob.getName(), product.getName())) {
                return false;
            }
        }
        korv.add(product);
        return true;
    }

    /***
     * po
     * @param name
     * @param maxPrice
     * @return
     */
    public Optional<Product> sellProduct(String name, int maxPrice) {
//        Integer pr = 0;
//        Product needed = null;
//        for (Product ob : korv) {
//            if (ob.getPrice() <= maxPrice && Objects.equals(ob.getName(), name)) {
//                if (ob.getPrice() > pr) {
//                    pr = ob.getPrice();
//                    needed = ob;
//                }
//            }
//        }
//        if (needed == null) {
//            return Optional.empty();
//        } else {
//            korv.remove(needed);
//            return Optional.of(needed);
//        }
        return Optional.empty();
    }

    /***
     * gh
     * @return
     */
    public List<Product> getProducts() {
        return korv;
    }

    /***
     * test
     * @param args
     */
    public static void main(String[] args) {

        Shop shop = new Shop();
        Product p1 = new Product("Bread", 3);
        Product p2 = new Product("Milk", 4);
        Product p3 = new Product("Milk", 4);
        Product p4 = new Product("Milk", 7);
        Product p5 = new Product("Cheat", -1);
        Product p6 = new Product("", 2);
        Product p7 = new Product(11);
        System.out.println(shop.addProduct(p1));  // true
        System.out.println(shop.addProduct(p2));  // true
        System.out.println(shop.addProduct(p3));  // false
        System.out.println(shop.addProduct(p4));  // true
        System.out.println(shop.addProduct(p5));  // false
        System.out.println(shop.addProduct(p6));  // true
        System.out.println(shop.addProduct(p7));  // true
        System.out.println(shop.sellProduct("Pizza", 10));  // Optional.empty
        System.out.println(shop.sellProduct("Milk", 10).get());  // Milk (7)
        System.out.println(shop.sellProduct("Milk", 10).get());  // Milk (4)
        System.out.println(shop.sellProduct("Milk", 10));  // Optional.empty
        System.out.println(shop.sellProduct("", 20).get());  //  (2)
    }
}
