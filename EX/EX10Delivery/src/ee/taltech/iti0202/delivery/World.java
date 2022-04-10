package ee.taltech.iti0202.delivery;

import java.util.*;

public class World {

    private final Map<String, Location> locations = new HashMap<>();
    private final Map<String, Courier> couriers = new HashMap<>();

    public Optional<Location> addLocation(String name, List<String> otherLocations, List<Integer> distances) {

        if ( locations.containsKey(name) || otherLocations.size() != distances.size() || locations.size() != otherLocations.size() ||
                !locations.keySet().containsAll(otherLocations)) {
            System.out.println("Empty");
            return Optional.empty();
        } else {
            Location newLocation = new Location(name);
            for (int i = 0; i < otherLocations.size(); i++) {
                newLocation.addDistance(otherLocations.get(i), distances.get(i));
                locations.get(otherLocations.get(i)).addDistance(name, distances.get(i));
            }
            locations.put(name, newLocation);
            return Optional.of(newLocation);
        }
    }

    public Optional<Courier> addCourier(String name, String to) {
        if (!locations.containsKey(to) || couriers.containsKey(name)) {
            return Optional.empty();
        } else {
            Courier courier = new Courier(name, locations.get(to));
            couriers.put(name, courier);
            return Optional.of(courier);
        }
    }

    public boolean giveStrategy(String name, Strategy strategy) {
        if (couriers.containsKey(name)) {
            couriers.get(name).setStrategy(strategy);
            return true;
        }
        return false;
    }

    public void tick() {
        for (Map.Entry<String, Courier> person : couriers.entrySet()) {
            if (person.getValue().getLocation().isPresent()) {
                Location l = person.getValue().getLocation().get();
                Strategy s = person.getValue().getStrategy();
                Action a = s.getAction();
                for (String name : a.getDeposit()) {
                    for (Packet pack : person.getValue().getPackets()) {
                        if (Objects.equals(pack.getName(), name)) {
                            l.addPacket(pack);
                            person.getValue().removePackets(pack);
                            break;
                        }
                    }
                }
                for (String name : a.getTake()) {
                    if (l.getPacket(name).isPresent()) {
                        person.getValue().addPackets(l.getPacket(name).get());
                        l.removePacket(l.getPacket(name).get());
                    }
                }
                person.getValue().setTargetLocation(a.getGoTo());
            }
            person.getValue().moveTo();
        }
    }
}
