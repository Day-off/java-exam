package ee.taltech.iti0202.kt.ship;

public class ShipBuilder {
    private Ship.Type type;
    private String cin;
    private int maxWeight;
    private int maxContainerAmount;

    public ShipBuilder setType(Ship.Type type) {
        this.type = type;
        return this;
    }

    public ShipBuilder setCin(String cin) {
        this.cin = cin;
        return this;
    }

    public ShipBuilder setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
        return this;
    }

    public ShipBuilder setMaxContainerAmount(int maxContainerAmount) {
        this.maxContainerAmount = maxContainerAmount;
        return this;
    }

    public Ship createShip() {
        return new Ship(type, cin, maxWeight, maxContainerAmount);
    }
}