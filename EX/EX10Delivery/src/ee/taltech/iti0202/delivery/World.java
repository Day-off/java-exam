package ee.taltech.iti0202.delivery;

import java.util.*;

public class World {

    private final List<Location> locations = new ArrayList<>();
    private final List<Courier> couriers = new ArrayList<>();

    public Optional<Location> addLocation(String name, List<String> otherLocations, List<Integer> distances) {
        if (distances.size() != otherLocations.size() || locations.stream().anyMatch(c -> c.getName().equals(name))) {
            return Optional.empty();
        } else {
            Location location = new Location(name);
            for (int i = 0; i < otherLocations.size(); i++) {
                location.addDistance(otherLocations.get(i), distances.get(i));
            }
            locations.add(location);
            return Optional.of(location);
        }
    }

    public Optional<Courier> addCourier(String name, String to) {
        if (locations.stream().anyMatch(c -> c.getName().equals(name)) || couriers.stream()
                .anyMatch(c -> c.getName().equals(name))) {
            return Optional.empty();
        } else {
            Courier courier = new Courier(name, locations.stream()
                    .filter(item -> item.getName().equals(to))
                    .toList().get(0));
            couriers.add(courier);
            return Optional.of(courier);
        }
    }

    public boolean giveStrategy(String name, Strategy strategy) {
        if (couriers.stream().anyMatch(c -> c.getName().equals(name))) {
            couriers.stream()
                    .filter(item -> item.getName().equals(name))
                    .toList().get(0).setStrategy(strategy);
            return true;
        }
        return false;
    }

    public void tick() {
        for (Courier cor : couriers) {
            if (cor.getLocation().isPresent()) {
                Location l = cor.getLocation().get();
                Strategy s = cor.getStrategy();
                Action a = s.getAction();
                for (String name : a.getDeposit()) {
                    for (Packet pack : cor.getPackets()) {
                        if (Objects.equals(pack.getName(), name)) {
                            l.addPacket(pack);
                            cor.removePackets(pack);
                        }
                    }
                }
                for (String name : a.getTake()) {
                    if (l.getPacket(name).isPresent()) {
                        cor.addPackets(l.getPacket(name).get());
                        l.removePacket(l.getPacket(name).get());
                    }
                }
                cor.setTargetLocation(a.getGoTo());
            }
            cor.moveTo();
        }
    }

}
