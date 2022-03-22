package ee.taltech.iti0202.coffeeMachine;

import static ee.taltech.iti0202.example.Main.LOGGER;

public class CoffeeMachineBuilder {

    private CoffeeMachine.Type type;
    private int maxTrash = 5;
    private WaterTank water;

    public CoffeeMachineBuilder setType(CoffeeMachine.Type type) {
        this.type = type;
        return this;
    }

    public CoffeeMachineBuilder setMaxTrash(int maxTrash) {
        if (type == CoffeeMachine.Type.CAPSULE) {
            this.maxTrash = 10;
            LOGGER.info("Capsule machine set trash volume ");
        } else if (0 != maxTrash) {
            this.maxTrash = maxTrash;
            LOGGER.info("machine trash volume was specified volume is " + maxTrash);
        } else {
            this.maxTrash = 5;
            LOGGER.info("machine trash volume doesn't specified volume is " + maxTrash);
        }
        return this;
    }

    public CoffeeMachineBuilder setWater(WaterTank water) {
        this.water = water;
        return this;
    }

    public CoffeeMachine createCoffeeMachine() {
        return new CoffeeMachine(type, maxTrash, water);
    }
}
