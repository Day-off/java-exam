package ee.taltech.iti0202.computerstore.database;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ee.taltech.iti0202.computerstore.components.Component;
import ee.taltech.iti0202.computerstore.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerstore.exceptions.ProductAlreadyExistsException;
import ee.taltech.iti0202.computerstore.exceptions.ProductNotFoundException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Database {
    private static Database instance = null;
    private Map<Integer, Component> components = new HashMap<>();

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
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
        try {
            Gson gson = new Gson();
            List<Component> comp = components.values().stream().toList();
            gson.toJson(comp, new FileWriter(location));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile(String location) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(location));
            List<Component> componentsList = new Gson().fromJson(reader, new TypeToken<List<Component>>() {
            }.getType());
            components = componentsList.stream()
                    .collect(Collectors.toMap(Component::getId, Function.identity()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}