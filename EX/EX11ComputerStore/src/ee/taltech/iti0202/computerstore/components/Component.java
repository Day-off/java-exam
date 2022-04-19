package ee.taltech.iti0202.computerstore.components;

import java.math.BigDecimal;

public class Component {
    private int id;
    private static int globalid;
    private String name;
    private Type type;
    private BigDecimal price;
    private int amount = 1;
    private String manufacturer;
    private int performancePoints;
    private int powerConsumption;


    public enum Type {
        CPU, GPU, RAM, MOTHERBOARD, HDD, SSD, PSU, KEYBOARD, TOUCHPAD, SCREEN, BATTERY, FAN
    }

    public Component(String name, Type type, BigDecimal price, String manufacturer,
                     int performancePoints, int powerConsumption) {
        id = setId();
        setnextId();
        this.name = name;
        this.type = type;
        this.price = price;
        this.manufacturer = manufacturer;
        this.performancePoints = performancePoints;
        this.powerConsumption = powerConsumption;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public static void idReset() {
        globalid = 0;
    }

    public static void setnextId() {
        globalid += 1;
    }

    public int setId() {
        id = globalid;
        return id;
    }

    public void setPerformancePoints(int performancePoints) {
        this.performancePoints = performancePoints;
    }

    public void setPowerConsumption(int powerConsumption) {
        this.powerConsumption = powerConsumption;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getPerformancePoints() {
        return performancePoints;
    }

    public int getPowerConsumption() {
        return powerConsumption;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n"
                + "Amount: " + amount + "\n" + "Id: " + id + "\n"
                + "Price: " + price + "\n";
    }
}
