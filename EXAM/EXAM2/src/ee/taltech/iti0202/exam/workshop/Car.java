package ee.taltech.iti0202.exam.workshop;
public class Car {
    private boolean isFixed = false;
    private int timeFixed = 0;

    public Car(String licencePlate) {
    }

    public boolean isFixed() {
        return isFixed;
    }

    public void setFix(boolean fixed) {
        isFixed = fixed;
    }

    public void carRepaired(){
        timeFixed += 1;
    }

    public int getTimeFixed() {
        return timeFixed;
    }

    public String getLicencePlate() {
        return "";
    }

}