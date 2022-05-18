package ee.taltech.iti0202.exam.workshop;

public class Car {
    private boolean isFixed = false;
    private int timeFixed = 0;

    private String license;

    public Car(String licencePlate) {
        license = licencePlate;
    }

    public boolean isFixed() {
        return isFixed;
    }

    public void setFix(boolean fixed) {
        isFixed = fixed;
    }

    public void carRepaired() {
        timeFixed += 1;
    }

    public int getTimeFixed() {
        return timeFixed;
    }

    public String getLicencePlate() {
        return license;
    }

}
