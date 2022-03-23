package ee.taltech.iti0202.coffeeMachine;

import static ee.taltech.iti0202.example.Main.logger;

public class CoffeeMachineBuilder {

    private CoffeeMachine.Type type;
    private int maxTrash = 5;
    private WaterTank water;
    private CoffeeBeans beans;

    public CoffeeMachineBuilder setType(CoffeeMachine.Type type) {
        this.type = type;
        return this;
    }

    public CoffeeMachineBuilder setMaxTrash(int maxTrash) {
        if (type == CoffeeMachine.Type.CAPSULE) {
            this.maxTrash = 10;
            logger.info("Capsule machine set trash volume ");
        } else if (0 != maxTrash) {
            this.maxTrash = maxTrash;
            logger.info("machine trash volume was specified volume is " + maxTrash);
        }
        return this;
    }

    public CoffeeMachineBuilder setWater(WaterTank water) {
        this.water = water;
        return this;
    }

    public CoffeeMachineBuilder setBeans(CoffeeBeans beans) {
        this.beans = beans;
        return this;
    }

    public CoffeeMachine createCoffeeMachine() {
        return new CoffeeMachine(type, maxTrash, water, beans);
    }
}
