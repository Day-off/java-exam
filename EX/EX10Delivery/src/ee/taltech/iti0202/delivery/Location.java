package ee.taltech.iti0202.delivery;

import java.util.*;

public class Location {

    private final String name;

    private final ArrayList<Packet> packets = new ArrayList<>();

    private final Map<String, Integer> othersLocations = new HashMap<>();

    public Location(String name) {
        this.name = name;
    }

    public void addPacket(Packet packet) {
        if (!packets.contains(packet)) {
            packet.setTarget(this);
            packets.add(packet);
        }
    }

    public void removePacket(Packet packet) {
        packets.remove(packet);
    }

    public Integer getDistanceTo(String name) {
        if (othersLocations.containsKey(name)){
            return othersLocations.get(name);
        }
        return Integer.MAX_VALUE;
    }

    public void addDistance(String location, int distance) {
        if (!othersLocations.containsKey(location)) {
            othersLocations.put(location, distance);
        }
    }

    public Optional<Packet> getPacket(String name) {
        return packets
                .stream()
                .filter(c -> c.getName().equals(name))
                .findAny();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name.toUpperCase(Locale.ROOT) + " Packets: " + packets;
    }
}
