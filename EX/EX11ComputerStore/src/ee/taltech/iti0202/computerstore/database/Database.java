package ee.taltech.iti0202.computerstore.database;

import ee.taltech.iti0202.computerstore.components.Component;
import ee.taltech.iti0202.computerstore.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerstore.exceptions.ProductAlreadyExistsException;
import ee.taltech.iti0202.computerstore.exceptions.ProductNotFoundException;


import java.util.HashMap;
import java.util.Map;

public class Database {
    private final Map<Integer, Component> components = new HashMap<>();

    public static Database getInstance() {
        return null;
    }

    public void saveComponent(Component component) throws ProductAlreadyExistsException {
        try {
            if (!components.containsKey(component.getId())) {
                components.put(component.getId(), component);
            }
        } catch (Exception e) {
            throw new ProductAlreadyExistsException();
        }
    }

    public void deleteComponent(int id) throws ProductNotFoundException {
        try {
            components.remove(id);
        } catch (Exception e) {
            throw new ProductNotFoundException();
        }
    }

    public void increaseComponentStock(int id, int amount) throws ProductNotFoundException {
        try {
            components.get(id).increaseAmount(amount);
        } catch (Exception e) {
            throw new ProductNotFoundException();
        }

    }

    public void decreaseComponentStock(int id, int amount) throws OutOfStockException, ProductNotFoundException {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        } else if (!components.containsKey(id)) {
            throw new ProductNotFoundException();
        } else if (components.get(id).getAmount() > amount) {
            throw new OutOfStockException();
        } else {
            components.get(id).decreaseAmount(amount);
        }
    }

    public Map<Integer, Component> getComponents() {
        return components;
    }

    public void resetEntireDatabase() {
        components.clear();
    }

    public void saveToFile(String location) {

    }

    public void loadFromFile(String location) {
    }
}