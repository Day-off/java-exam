package ee.taltech.iti0202.coffeeMachine;

import ee.taltech.iti0202.drinks.Drinks;

import static ee.taltech.iti0202.example.Main.LOGGER;


public class Capsule {


    private boolean isUsed = false;

    public Capsule(Drinks.DrinksTypes flavor) {
        LOGGER.info("Capsule created");
    }

    public void useCapsule() {
        LOGGER.info("Capsule was used");
        this.isUsed = true;
    }

    public boolean getIsUsed() {
        return this.isUsed;
    }
}