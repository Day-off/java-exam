package ee.taltech.iti0202.coffeeMachine;

import java.util.logging.Level;
import java.util.logging.Logger;

import static ee.taltech.iti0202.example.Main.LOGGER;

public class WaterTank {

    private int litersOfWater;
    private final int containerVolume;

    public WaterTank(int liters) {
        this.containerVolume = liters;
        setLitersOfWater(containerVolume);
        LOGGER.info("Water tank was created volume " + containerVolume);
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

    public void reduceVolume(){
        litersOfWater -= 1;
        LOGGER.info("Water was reduce");
    }

    public void refillTank() {
        this.litersOfWater = containerVolume;
        LOGGER.info("Water tank was refilled");
    }
}
