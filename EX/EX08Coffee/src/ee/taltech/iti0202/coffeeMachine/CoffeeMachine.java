package ee.taltech.iti0202.coffeeMachine;

import ee.taltech.iti0202.drinks.Drinks;

import java.util.ArrayList;
import java.util.logging.Level;

import static ee.taltech.iti0202.example.Main.LOGGER;


public class CoffeeMachine {
    private int totalTrash = 0;
    private final Integer maxTrash;
    private final Type type;
    private final WaterTank water;

    private boolean isFull = false;

    private final ArrayList<Capsule> compartment = new ArrayList<>();


    public enum Type {
        ORDINARY, AUTOMATIC, CAPSULE
    }

    public CoffeeMachine(Type type, int maxTrash, WaterTank water) {
        this.type = type;
        this.maxTrash = maxTrash;
        this.water = water;
        LOGGER.info("Coffee machine was created");
    }

    /*
    GENERAL METHODS
     */

    public boolean checkTrash() {
        if (totalTrash == maxTrash) {
            this.isFull = true;
            return true;
        }
        return false;
    }

    public void cleanTrashTank() {
        totalTrash = 0;
        LOGGER.info("Trash tank was cleaned");
        this.isFull = false;
    }

    public Drinks start(Drinks.DrinksTypes drink) {
        checkTrash();
        if (type.equals(Type.AUTOMATIC)) {
            totalTrash += 1;
            LOGGER.info("AUTO.Coffee machine was created the " + drink.name());
            return new Drinks(drink);
        } else if (type.equals(Type.ORDINARY) && !isFull && this.water.checkVolume()) {
            this.water.reduceVolume();
            totalTrash += 1;
            LOGGER.info("ORD.Coffee machine was created the " + drink.name());
            return new Drinks(drink);
        } else {
            return startCapsule(drink);
        }
    }

    /*
     METHODS FOR CAPSULE MACHINE
     */

    public boolean setCapsule(Capsule capsule) {
        if (compartment.size() == 0) {
            compartment.add(capsule);
            LOGGER.info("Capsule was added");
            return true;
        } else {
            LOGGER.log(Level.WARNING, "Remove old capsule",
                    new RuntimeException("Compartment already contains capsule"));
        }
        return false;
    }

    public void removeCapsule() {
        if (compartment.size() != 0) {
            LOGGER.info("Capsule was removed");
            compartment.remove(compartment.get(0));
        }
    }

    public Drinks startCapsule(Drinks.DrinksTypes flavor) {
        if (isFull) {
            LOGGER.log(Level.WARNING, "Trash tank of " + this + " is full, clean trash tank",
                    new Throwable("Trash is full!"));
            return null;
        } else {
            if (this.type.equals(Type.CAPSULE) && this.compartment.size() == 0) {
                LOGGER.log(Level.WARNING, "Machine doesn't contains capsule");
            }
            if (this.type.equals(Type.CAPSULE) && this.water.checkVolume()
                    && (this.compartment.size() == 0 || this.compartment.get(0).getIsUsed())) {
                this.water.reduceVolume();
                LOGGER.info("You use capsule coffee machine. "
                        + "Old capsule in use or there is no capsule in the compartment. Dink is water");
                return new Drinks(Drinks.DrinksTypes.WATER);
            } else if (!this.water.checkVolume()) {
                LOGGER.log(Level.WARNING, "Refill water tank", new Throwable("Water tank is empty"));
                return null;
            } else {
                this.compartment.get(0).useCapsule();
                this.water.reduceVolume();
                totalTrash += 1;
                LOGGER.info("CAPS.Coffee machine was created the " + flavor.name());
                return new Drinks(flavor);

            }
        }
    }

    /*
    GETTER
     */

    public Type getType() {
        return type;
    }

    public WaterTank getWater() {
        return water;
    }
}
