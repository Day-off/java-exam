package ee.taltech.iti0202.exam.workshop;

import java.util.ArrayList;
import java.util.List;

public class Mechanic {

    private final List<Car> carsToBeFixed = new ArrayList<>();
    private final List<Car> carsFixed = new ArrayList<>();
    private final String name;
    private Workshop workshop;

    public Mechanic(String name) {
        this.name = name;
    }

    public boolean fixCar(Car car) {
        if (car.isFixed() || !carsToBeFixed.contains(car)) {
            return false;
        }
        car.setFix(true);
        carsFixed.add(car);
        carsToBeFixed.remove(car);
        return true;
    }

    public List<Car> getCarsToBeFixed() {
        return carsToBeFixed;
    }

    public void addCar(Car car) {
        carsToBeFixed.add(car);
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }

    public Workshop getWorkshop() {
        return workshop;
    }

    public String getName() {
        return name;
    }

}
