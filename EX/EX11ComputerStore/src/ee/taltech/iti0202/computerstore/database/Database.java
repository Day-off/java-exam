package ee.taltech.iti0202.computerstore.database;

import com.google.gson.Gson;
import ee.taltech.iti0202.computerstore.components.Component;
import ee.taltech.iti0202.computerstore.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerstore.exceptions.ProductAlreadyExistsException;
import ee.taltech.iti0202.computerstore.exceptions.ProductNotFoundException;

import java.io.FileWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Database {
    private static Database instance = null;
    private Map<Integer, Component> components = new HashMap<>();

    private Database() {

    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void saveComponent(Component component) throws ProductAlreadyExistsException {
        if (!components.containsValue(component)) {
            components.put(component.getId(), component);
        } else {
            throw new ProductAlreadyExistsException();
        }
    }

    public void deleteComponent(int id) throws ProductNotFoundException {
        if (!components.containsKey(id)) {
            throw new ProductNotFoundException();
        } else {
            components.remove(id);
        }
    }

    public void increaseComponentStock(int id, int amount) throws ProductNotFoundException {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }
        if (!components.containsKey(id)) {
            throw new ProductNotFoundException();
        } else {
            components.get(id).setAmount(components.get(id).getAmount() + amount);
        }

    }

    public void decreaseComponentStock(int id, int amount) throws OutOfStockException, ProductNotFoundException {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        } else if (!components.containsKey(id)) {
            throw new ProductNotFoundException();
        } else if (components.get(id).getAmount() < amount) {
            throw new OutOfStockException();
        } else {
            components.get(id).setAmount(components.get(id).getAmount() - amount);
        }
    }

    public Map<Integer, Component> getComponents() {
        return components;
    }

    public void resetEntireDatabase() {
        Component.idReset();
        components.clear();
    }

    public void saveToFile(String location) {
        try {
            Gson gson = new Gson();
            gson.toJson(Database.getInstance().components.values(), new FileWriter(location));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile(String location) {
        try {
            // create Gson instance
            Gson gson = new Gson();
            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get(location));

            instance = gson.fromJson(reader, Database.class);
            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}