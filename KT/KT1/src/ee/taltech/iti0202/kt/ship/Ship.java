package ee.taltech.iti0202.kt.ship;

import java.util.ArrayList;
import java.util.List;

public class Ship {

    private final Type type;
    private final String cin;
    private final int maxWeight;
    private final int maxContainerAmount;
    private final List<Container> containers = new ArrayList<>();

    public enum Type {
        TANKER, PASSENGER, BULK
    }

    public Ship(Type type, String cin, int maxWeight, int maxContainerAmount) {
        this.type = type;
        this.cin = cin;
        this.maxWeight = maxWeight;
        this.maxContainerAmount = maxContainerAmount;
    }

    public Type getType() {
        return type;
    }

    public String getCin() {
        return cin;
    }

    public boolean addContainer(Container container) {
        if (!containers.contains(container) && checkWeight(container)) {
            if (this.maxContainerAmount == 0 || checkAmount()) {
                containers.add(container);
                return true;
            }
        }
        return false;
    }

    public boolean checkWeight(Container container) {
        int currentWeight = 0;
        for (Container c : this.containers) {
            currentWeight += c.getWeight();
        }
        return this.maxWeight >= currentWeight + container.getWeight();
    }

    public boolean checkAmount() {
        return this.maxContainerAmount >= containers.size() + 1;
    }

    public int getMaxContainerAmount() {
        return maxContainerAmount;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public List<Container> getContainers() {
        return containers;
    }
}
