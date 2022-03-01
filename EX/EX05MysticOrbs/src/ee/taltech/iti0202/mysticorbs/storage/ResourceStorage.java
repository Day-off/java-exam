package ee.taltech.iti0202.mysticorbs.storage;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ResourceStorage {

    private final Map<String, Integer> storage = new HashMap<>();

    /***
     * constructor
     */
    public ResourceStorage() {

    }

    /***
     * check empty
     */
    public boolean isEmpty() {
        for (Map.Entry<String, Integer> source : storage.entrySet()) {
            if (source.getValue() >= 1) {
                return false;
            }
        }
        return true;
    }

    /***
     * add resource
     */
    public void addResource(String resource, int amount) {
        if (resource.trim().length() > 0 && amount >= 0) {
            if (storage.containsKey(resource.toLowerCase(Locale.ROOT))) {
                storage.put(resource.toLowerCase(Locale.ROOT), storage.get(resource.toLowerCase(Locale.ROOT)) + amount);
            } else {
                storage.put(resource.toLowerCase(Locale.ROOT), amount);
            }
        }
    }

    /***
     * getter
     */
    public int getResourceAmount(String resource) {
        return storage.getOrDefault(resource.toLowerCase(Locale.ROOT), 0);
    }

    /***
     * check resource
     */
    public boolean hasEnoughResource(String resource, int amount) {
        if (amount >= 1 && storage.containsKey(resource.toLowerCase(Locale.ROOT))) {
            return storage.get(resource.toLowerCase(Locale.ROOT)) >= amount;

        }
        return false;
    }

    /***
     * take resource
     */
    public boolean takeResource(String resource, int amount) {
        if (hasEnoughResource(resource, amount)) {
            storage.put(resource.toLowerCase(Locale.ROOT), storage.get(resource.toLowerCase(Locale.ROOT)) - amount);
            return true;
        } else {
            return false;
        }
    }
}
