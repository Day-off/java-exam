package ee.taltech.iti0202.exam.workshop;

import java.util.*;

public class Workshop {

    private String name;
    private List<Mechanic> stuff = new ArrayList<>();

    private List<Car> allCars = new ArrayList<>();

    public Workshop(String name) {
        this.name = name;
    }

    public boolean addMechanic(Mechanic mechanic) {
        if (!stuff.contains(mechanic) && mechanic.getWorkshop() == null){
            stuff.add(mechanic);
            mechanic.setWorkshop(this);
            return true;
        }
        return false;
    }

    public boolean registerCarForRepair(Car car, Mechanic mechanic) {
        if (!stuff.contains(mechanic) || mechanic.getCarsToBeFixed().contains(car) || !car.isFixed()){
            return false;
        }
        mechanic.addCar(car);
        allCars.add(car);
        return true;
    }

    public Optional<Car> getCarWithTheMostFixedTimes() {
        List<Car> fixedCars = new ArrayList<>();
        for (Car car: allCars){
            if (car.isFixed()){
                fixedCars.add(car);
            }
        }
        Optional<Car> mostFixed = Optional.of(Collections.max(fixedCars, Comparator.comparing(Car::getTimeFixed)));
        return mostFixed;
    }

    public List<Mechanic> getAllMechanics() {
        return stuff;
    }

    public String getName() {
        return name;
    }
}
