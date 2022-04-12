package ee.taltech.iti0202.delivery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class World {

    private final HashMap<String, Location> locations = new HashMap<>();
    private final HashMap<String, Courier> couriers = new HashMap<>();

    public Optional<Location> addLocation(String name, List<String> otherLocations, List<Integer> distances) {
        if (locations.containsKey(name)
                || otherLocations.size() != distances.size()
                || isAllLocations(otherLocations)) {

            return Optional.empty();
        }
        Location newLocation = new Location(name);
        int size = locations.size();
        for (int i = 0; i < size; i++) {
            String city = otherLocations.get(i);
            int dist = distances.get(i);
            newLocation.addDistance(city, dist);
            locations.get(city).addDistance(name, dist);
        }
        locations.put(name, newLocation);
        return Optional.of(newLocation);
    }

    public boolean isAllLocations(List<String> otherLocations) {
        int count = 0;
        for (String loc : otherLocations) {
            if (locations.containsKey(loc)) {
                count += 1;
            }
        }
        return count != locations.size();
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

                Location corLocation = cor.getLocation().get();
                Action action = cor.getStrategy().getAction();

                for (String name : action.getDeposit()) {
                    if (cor.getPacket(name).isPresent()) {
                        corLocation.addPacket(cor.getPacket(name).get());
                        cor.removePacket(cor.getPacket(name).get());
                    }
                }

                for (String name : action.getTake()) {
                    corLocation.getPacket(name).ifPresentOrElse(cor::addPackets, () -> {
                    });
                }
                cor.setTargetLocation(action.getGoTo());
            }
            cor.moveTo();
        }
    }
}
