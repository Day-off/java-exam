package ee.taltech.iti0202.delivery;

import java.util.*;

public class World {

    private final HashMap<String, Location> locations = new HashMap<>();
    private final HashMap<String, Courier> couriers = new HashMap<>();

    public Optional<Location> addLocation(String name, List<String> otherLocations, List<Integer> distances) {
        if (locations.containsKey(name)
                || otherLocations.size() != distances.size()
                || !otherLocations.containsAll(locations.keySet())) {

            return Optional.empty();
        }
        Location newLocation = new Location(name);
        int size = locations.size();
        for (int i = 0; i < size; i++) {
            String city = otherLocations.get(i);
            int dist = distances.get(i);
            if (locations.containsKey(city)) {
                newLocation.addDistance(city, dist);
                locations.get(city).addDistance(name, dist);
            }
        }
        locations.put(name, newLocation);
        return Optional.of(newLocation);
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
            Courier cor = person.getValue();
            if (cor.getLocation().isPresent()) {
                Location l = cor.getLocation().get();
                Strategy s = cor.getStrategy();
                Action a = s.getAction();
                for (String name : a.getDeposit()) {
                    if (cor.getPacket(name).isPresent()){
                        l.addPacket(cor.getPacket(name).get());
                        cor.removePacket(cor.getPacket(name).get());
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
