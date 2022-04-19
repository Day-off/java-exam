package ee.taltech.iti0202.computerstore.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ee.taltech.iti0202.computerstore.components.Component;
import ee.taltech.iti0202.computerstore.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerstore.exceptions.ProductAlreadyExistsException;
import ee.taltech.iti0202.computerstore.exceptions.ProductNotFoundException;

import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Database {
    private static Database instance = null;
    private Map<Integer, Component> components = new HashMap<>();

    private Database() {

    }

    /***
     * SingleTone
     * @return
     */
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    /***
     * Save component to map
     * @param component
     * @throws ProductAlreadyExistsException
     */
    public void saveComponent(Component component) throws ProductAlreadyExistsException {
        if (!components.containsValue(component)) {
            components.put(component.getId(), component);
        } else {
            throw new ProductAlreadyExistsException();
        }
    }

    /***
     * delete component from map
     * @param id
     * @throws ProductNotFoundException
     */
    public void deleteComponent(int id) throws ProductNotFoundException {
        if (!components.containsKey(id)) {
            throw new ProductNotFoundException();
        } else {
            components.remove(id);
        }
    }

    /***
     * Increase component amount
     * @param id
     * @param amount
     * @throws ProductNotFoundException
     */
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

    /***
     * Decrease component amount
     * @param id
     * @param amount
     * @throws ProductNotFoundException
     */
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

    /***
     * Resets id and database
     */
    public void resetEntireDatabase() {
        Component.idReset();
        components.clear();
    }

    /***
     * Write database to file
     * @param location
     */
    public void saveToFile(String location) {
        try (Writer writer = new FileWriter(location)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(Database.getInstance(), writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * Read file to database
     * @param location
     */
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
