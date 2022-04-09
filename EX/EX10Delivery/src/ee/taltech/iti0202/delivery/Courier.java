package ee.taltech.iti0202.delivery;

import java.util.ArrayList;
import java.util.Optional;

public class Courier {

    private final String name;
    private final ArrayList<Packet> packets = new ArrayList<>();
    private Location currentLocation;
    private Location direction;
    private int distanceToTarget;
    private Strategy strategy;

    public Courier(String name, Location currentLocation) {
        this.name = name;
        this.currentLocation = currentLocation;
    }

    public Optional<Location> getLocation() {
        if (currentLocation == null) {
            return Optional.empty();
        }
        return Optional.of(currentLocation);
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getName() {
        return name;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public ArrayList<Packet> getPackets() {
        return packets;
    }

    public void addPackets(Packet pack) {
        if (!packets.contains(pack)) {
            packets.add(pack);
        }
    }

    public void removePackets(Packet pack) {
        packets.remove(pack);
    }

    public void setTargetLocation(Location location) {
        if (direction == null || direction == currentLocation && getLocation().isPresent()) {
            direction = location;
            distanceToTarget = getLocation().get().getDistanceTo(location.getName());
            setCurrentLocation(null);
        }
    }

    public int getDistanceToTarget() {
        return distanceToTarget;
    }

    public void moveTo() {
        if (distanceToTarget != 0) {
            distanceToTarget -= 1;
        }
        if (distanceToTarget == 0) {
            setCurrentLocation(direction);
        }

    }

    @Override
    public String toString() {
        return name + " (" + currentLocation + "). "
                + "Packets: " + packets;
    }
}