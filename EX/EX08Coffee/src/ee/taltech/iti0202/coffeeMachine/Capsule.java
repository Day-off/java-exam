package ee.taltech.iti0202.coffeeMachine;

import ee.taltech.iti0202.drinks.Drinks;

import static ee.taltech.iti0202.example.Main.logger;


public class Capsule {


    private boolean isUsed = false;

    public Capsule(Drinks.DrinksTypes flavor) {
        logger.info("Capsule created");
    }

    public void useCapsule() {
        logger.info("Capsule was used");
        this.isUsed = true;
    }

    public boolean getIsUsed() {
        return this.isUsed;
    }
}
