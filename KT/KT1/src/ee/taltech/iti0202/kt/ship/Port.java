package ee.taltech.iti0202.kt.ship;

import java.util.ArrayList;
import java.util.List;

public class Port {

    private final List<Container> containers = new ArrayList<>();
    private final List<Ship> ships = new ArrayList<>();

    public Port() {

    }

    public void addContainer(Container container) {
        if (!containers.contains(container)) {
            containers.add(container);
        }
    }

    public void addShip(Ship ship) {
        if (!ships.contains(ship)) {
            ships.add(ship);
        }
    }

    public void divideContainers() {
        List<Container> copyCon = List.copyOf(this.containers);
        for (Container con : copyCon) {
            for (Ship s : this.ships) {
                if (s.addContainer(con)) {
                    con.changeStatuseOnShip();
                    this.containers.remove(con);
                    break;

                }
            }
        }
    }

    public List<Container> getContainers() {
        return containers;
    }

    public List<Ship> getShips() {
        return ships;
    }
}
