package ee.taltech.iti0202.mysticorbs.storage;

import java.util.HashMap;
import java.util.Map;

public class ResourceStorage {

    private Map<String, Integer> storage = new HashMap<>();

    public ResourceStorage() {

    }

    public boolean isEmpty() {
        for (Map.Entry<String, Integer> source : storage.entrySet()) {
            if (source.getValue() <= 1) {
                return false;
            }
        }
        return true;
    }

    public void addResource(String resource, int amount) {
        if (storage.containsKey(resource)) {
            storage.put(resource, storage.get(resource) + amount);
        } else {
            storage.put(resource, amount);
        }
    }

    public int getResourceAmount(String resource) {
        return storage.getOrDefault(resource, 0);
    }

    public boolean hasEnoughResource(String resource, int amount) {
        if (amount < 1 || !storage.containsKey(resource)) {
            return false;
        } else {
            return storage.get(resource) >= amount;
        }
    }

    public boolean takeResource(String resource, int amount) {
        if (hasEnoughResource(resource, amount)) {
            storage.put(resource, storage.get(resource) - amount);
            return true;
        } else {
            return false;
        }
    }
}
