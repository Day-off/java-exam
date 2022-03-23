package ee.taltech.iti0202.kitchen;

import ee.taltech.iti0202.coffeeMachine.CoffeeMachine;
import ee.taltech.iti0202.drinks.Drinks;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static ee.taltech.iti0202.example.Main.logger;

public class Kitchen {


    private List<CoffeeMachine> coffeeMachines = new ArrayList();

    public Kitchen(List<CoffeeMachine> machines) {
        coffeeMachines = machines;
        logger.info("New caffe was created");
    }

    public void addCoffeeMachines(CoffeeMachine coffeeMachine) {
        this.coffeeMachines.add(coffeeMachine);
        logger.info("Coffee machine was added");
    }

    public void removeCoffeeMachines(CoffeeMachine coffeeMachine) {
        this.coffeeMachines.remove(coffeeMachine);
        logger.info("Coffee machine was removed");
    }

    public Drinks order(CoffeeMachine machine, Drinks.DrinksTypes drink) {
        for (CoffeeMachine machin : coffeeMachines) {
            if (machin == machine) {
                logger.info(machin.getType().name() + " try create " + drink.name());
                return machin.start(drink);
            }
        }
        logger.log(Level.WARNING, "There is no machine or drink of the required type",
                new Throwable("Not required type"));
        return null;
    }

    public List<CoffeeMachine> getCoffeeMachines() {
        return coffeeMachines;
    }
}
