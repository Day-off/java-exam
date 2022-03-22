package ee.taltech.iti0202.kitchen;

import ee.taltech.iti0202.coffeeMachine.CoffeeMachine;
import ee.taltech.iti0202.drinks.Drinks;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static ee.taltech.iti0202.example.Main.LOGGER;

public class Kitchen {


    private List<CoffeeMachine> coffeeMachines = new ArrayList();

    public Kitchen(List<CoffeeMachine> machines) {
        coffeeMachines = machines;
        LOGGER.info("New caffe was created");
    }

    public void addCoffeeMachines(CoffeeMachine coffeeMachine) {
        this.coffeeMachines.add(coffeeMachine);
        LOGGER.info("Coffee machine was added");
    }

    public void removeCoffeeMachines(CoffeeMachine coffeeMachine) {
        this.coffeeMachines.remove(coffeeMachine);
        LOGGER.info("Coffee machine was removed");
    }

    public Drinks order(CoffeeMachine machine, Drinks.DrinksTypes drink) {
        for (CoffeeMachine machin : coffeeMachines) {
            if (machin == machine) {
                LOGGER.info(machin.getType().name() + " try create " + drink.name());
                return machin.start(drink);
            }
        }
        LOGGER.log(Level.WARNING, "There is no machine or drink of the required type", new Throwable("Not required type"));
        return null;
    }

    public List<CoffeeMachine> getCoffeeMachines() {
        return coffeeMachines;
    }
}
