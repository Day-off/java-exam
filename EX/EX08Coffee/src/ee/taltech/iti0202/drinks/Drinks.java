package ee.taltech.iti0202.drinks;

import ee.taltech.iti0202.coffeeMachine.Capsule;

import java.util.logging.Logger;

public class Drinks {

    private final DrinksTypes type;

    public enum DrinksTypes{
        COFFEE, KAKAO, CAPPUCCINO, LATTE, WATER
    }
    public Drinks(DrinksTypes type){
        this.type = type;
    }

    public DrinksTypes getType() {
        return type;
    }
}
