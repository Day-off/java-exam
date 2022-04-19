package ee.taltech.iti0202.computerstore.store;

import ee.taltech.iti0202.computerstore.Customer;
import ee.taltech.iti0202.computerstore.components.Component;
import ee.taltech.iti0202.computerstore.database.Database;
import ee.taltech.iti0202.computerstore.exceptions.NotEnoughMoneyException;
import ee.taltech.iti0202.computerstore.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerstore.exceptions.ProductNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Store {


    private String name;
    private BigDecimal balance;
    private BigDecimal profitMargin;

    public Store(String name, BigDecimal balance, BigDecimal profitMargin) {
        this.name = name;
        this.balance = balance;
        setProfitMargin(profitMargin);

    }

    public Component purchaseComponent(int id, Customer customer) throws OutOfStockException,
            ProductNotFoundException,
            NotEnoughMoneyException {

        if (!Database.getInstance().getComponents().containsKey(id)) {
            throw new ProductNotFoundException();
        }
        Component product = Database.getInstance().getComponents().get(id);

        if (product.getAmount() < 1) {
            throw new OutOfStockException();
        }

        if (customer.getBalance().compareTo(profitMargin.multiply(product.getPrice().multiply(BigDecimal.valueOf(product.getAmount())))) < 0) {
            throw new NotEnoughMoneyException();
        } else {
            Database.getInstance().decreaseComponentStock(id, product.getAmount());
            this.balance = balance.subtract(product.getPrice());
            customer.addComponent(product);
            return product;
        }
    }

    public List<Component> getAvailableComponents() {
        List<Component> l = new ArrayList<>();
        for (int id : Database.getInstance().getComponents().keySet()) {
            Component com = Database.getInstance().getComponents().get(id);
            if (com.getAmount() > 0) {
                l.add(com);
            }
        }
        return l;
    }

    public List<Component> getComponentsSortedByAmount() {
        List<Component> no_filtered_list = new ArrayList<>(Database.getInstance().getComponents().values());
        return no_filtered_list.stream().sorted(Comparator.comparing(Component::getAmount)).collect(Collectors.toList());
    }

    public List<Component> getComponentsSortedByName() {
        List<Component> no_filtered_list = new ArrayList<>(Database.getInstance().getComponents().values());
        return no_filtered_list.stream().sorted(Comparator.comparing(Component::getName)).collect(Collectors.toList());
    }

    public List<Component> getComponentsSortedByPrice() {
        List<Component> no_filtered_list = new ArrayList<>(Database.getInstance().getComponents().values());
        return no_filtered_list.stream().sorted(Comparator.comparing(Component::getPrice)).collect(Collectors.toList());

    }

    public List<Component> filterByType(Component.Type type) {
        List<Component> no_filtered_list = new ArrayList<>(Database.getInstance().getComponents().values());
        return no_filtered_list.stream().filter(a -> a.getType() == type).toList();
    }

    public BigDecimal getInventoryValue() {
        return balance.add(profitMargin);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getProfitMargin() {
        return profitMargin;
    }

    public void setProfitMargin(BigDecimal profitMargin) {
        if (profitMargin.compareTo(BigDecimal.valueOf(1)) < 0) {
            throw new IllegalArgumentException();
        } else {
            this.profitMargin = profitMargin;
        }
    }
}