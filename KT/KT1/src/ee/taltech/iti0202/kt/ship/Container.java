package ee.taltech.iti0202.kt.ship;

public class Container {

    private final int weight;
    private boolean onShip = false;

    public Container(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void changeStatuseOnShip() {
        this.onShip = true;
    }

    public boolean getStatus() {
        return this.onShip;
    }
}
