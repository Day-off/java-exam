package ee.taltech.iti0202.coffeeMachine;

import static ee.taltech.iti0202.example.Main.logger;

public class WaterTank {

    private int litersOfWater;
    private final int containerVolume;

    public WaterTank(int liters) {
        this.containerVolume = liters;
        setLitersOfWater(containerVolume);
        logger.info("Water tank was created volume " + containerVolume);
    }

    public int getLitersOfWater() {
        return litersOfWater;
    }

    public void setLitersOfWater(int volume) {
        this.litersOfWater = containerVolume;
    }

    public boolean checkVolume() {
        return litersOfWater >= 1;
    }

    public void reduceVolume() {
        litersOfWater -= 1;
        logger.info("Water was reduce");
    }

    public void refillTank() {
        this.litersOfWater = containerVolume;
        logger.info("Water tank was refilled");
    }
}
