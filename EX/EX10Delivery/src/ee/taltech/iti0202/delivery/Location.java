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
        if (!packetsMap.containsValue(packet)) {
//            packet.setTarget(this);
            packetsMap.put(packet.getName(), packet);
        }
    }

    public void removePacket(Packet packet) {
        packetsMap.remove(packet.getName(), packet);
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
        return Optional.of(packetsMap.getOrDefault(name, null));
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name.toUpperCase(Locale.ROOT) + " Packets: " + packetsMap.keySet();
    }
}
