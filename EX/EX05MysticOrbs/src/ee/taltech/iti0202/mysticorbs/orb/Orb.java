package ee.taltech.iti0202.mysticorbs.orb;

import java.util.Locale;

public class Orb {

    protected String sourceOven;
    protected int charge;

    public Orb(String creator) {
        sourceOven = creator;
    }

    public void charge(String resource, int amount) {
        String dust = "dust";
        if (!resource.trim().isEmpty() && !resource.toLowerCase(Locale.ROOT).equals(dust)) {
            charge += resource.length() * amount;
        }
    }

    public int getEnergy() {
        return charge;
    }

    @Override
    public String toString() {
        return "Orb by " + sourceOven;
    }
}