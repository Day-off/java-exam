package ee.taltech.iti0202.computerstore.store;

import ee.taltech.iti0202.computerstore.Customer;
import ee.taltech.iti0202.computerstore.components.Component;
import ee.taltech.iti0202.computerstore.database.Database;
import ee.taltech.iti0202.computerstore.exceptions.NotEnoughMoneyException;
import ee.taltech.iti0202.computerstore.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerstore.exceptions.ProductNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Store {


    private String name;
    private BigDecimal balance;
    private BigDecimal profitMargin;

    public Store(String name, BigDecimal balance, BigDecimal profitMargin) {
        this.name = name;
        this.balance = balance;
        if (profitMargin.compareTo(new BigDecimal(1)) < 0) {
            throw new IllegalArgumentException();
        } else {
            this.profitMargin = profitMargin;
        }

    }

    public Component purchaseComponent(int id, Customer customer) throws OutOfStockException,
            ProductNotFoundException,
            NotEnoughMoneyException {
        Component product = Database.getInstance().getComponents().get(id);
        if (customer.getBalance().compareTo(profitMargin.multiply(product.getPrice())) < 0) {
            throw new NotEnoughMoneyException();
        } else {
            Database.getInstance().decreaseComponentStock(id, 1);
            this.balance = balance.subtract(product.getPrice());
            customer.addComponent(product);
            return product;
        }
    }

    public List<Component> getAvailableComponents() {
       return new ArrayList<>();
    }

    public List<Component> getComponentsSortedByAmount() {
        return new ArrayList<>();
    }

    public List<Component> getComponentsSortedByName() {
        return new ArrayList<>();
    }

    public List<Component> getComponentsSortedByPrice() {
        return new ArrayList<>();
    }

    public List<Component> filterByType(Component.Type type) {
        return new ArrayList<>();
    }

    public BigDecimal getInventoryValue() {
        return BigDecimal.ZERO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance.intValueExact();
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getProfitMargin() {
        return profitMargin.intValueExact();
    }

    public void setProfitMargin(BigDecimal profitMargin) {
        this.profitMargin = profitMargin;
    }
}