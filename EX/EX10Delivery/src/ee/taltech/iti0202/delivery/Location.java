package ee.taltech.iti0202.delivery;

import java.util.*;

public class Location {

    private final String name;

    private final Map<String, Integer> othersLocations = new HashMap<>();

    private final Map<String, Packet> packetsMap = new HashMap<>();

    public Location(String name) {
        this.name = name;
    }

    public void addPacket(Packet packet) {
        if (!packetsMap.containsKey(packet.getName())) {
            packetsMap.put(packet.getName(), packet);
        }
    }

    public Integer getDistanceTo(String name) {
        return othersLocations.getOrDefault(name, Integer.MAX_VALUE);
    }

    public void addDistance(String location, int distance) {
        if (!othersLocations.containsKey(location)) {
            othersLocations.put(location, distance);
        }
    }

    public Optional<Packet> getPacket(String name) {
        if (packetsMap.containsKey(name)){
            Packet pack = packetsMap.get(name);
            packetsMap.remove(name);
            return Optional.of(pack);
        }
        return Optional.empty();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name.toUpperCase(Locale.ROOT) + " Packets: " + packetsMap.keySet();
    }
}