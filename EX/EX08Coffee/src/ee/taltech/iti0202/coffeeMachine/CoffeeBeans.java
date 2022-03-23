package ee.taltech.iti0202.coffeeMachine;

import static ee.taltech.iti0202.example.Main.logger;

public class CoffeeBeans {
    private int beansAmount;
    private final int containerVolume;

    public CoffeeBeans(int liters) {
        this.containerVolume = liters;
        beansAmount = containerVolume;
        logger.info("Beans tank was created volume " + containerVolume);
    }

    public int getBeansAmount() {
        return beansAmount;
    }

    public boolean checkVolume() {
        return beansAmount >= 1;
    }

    public void reduceBeansVolume() {
        beansAmount -= 1;
        logger.info("Beans was reduce");
    }

    public void refillBeansTank() {
        this.beansAmount = containerVolume;
        logger.info("Beans tank was refilled");
    }
}
