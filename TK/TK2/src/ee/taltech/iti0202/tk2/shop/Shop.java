package ee.taltech.iti0202.tk2.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Shop {

    private List<Product> korv = new ArrayList<>();

    public Shop() {

    }

    public boolean addProduct(Product product) {
        if (product.getPrice() < 0) {
            return false;
        }
        for (Product ob : korv) {
            if (!Objects.equals(product, ob)) {
                korv.add(product);
                return true;
            }
        }
        return false;
    }

    public Optional<Product> sellProduct(String name, int maxPrice) {
        Integer pr = 0;
        Product needed = null;
        for (Product ob : korv) {
            if (ob.getPrice() <= maxPrice) {
                if (ob.getPrice() > pr) {
                    pr = ob.getPrice();
                    needed = ob;
                }
            }
        }
        if (needed == null) {
            return Optional.empty();
        } else {
            return Optional.of(needed);
        }
    }

    public List<Product> getProducts() {
        return korv;
    }
}
