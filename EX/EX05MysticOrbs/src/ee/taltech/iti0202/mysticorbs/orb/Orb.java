package ee.taltech.iti0202.mysticorbs.orb;

import java.util.Locale;

public class Orb {

    protected String sourceOven;
    protected int charge;
    public String sort;

    /***
     * constructor
     */
    public Orb(String creator) {
        sourceOven = creator;
        sort = "Orb by ";
    }

    /***
     * charge
     */
    public void charge(String resource, int amount) {
        String dust = "dust";
        if (!resource.trim().isEmpty() && !resource.toLowerCase(Locale.ROOT).equals(dust)
                && amount > 0) {
            charge += resource.length() * amount;
        }
    }

    /***
     * getter
     */
    public int getEnergy() {
        return charge;
    }

    /***
     * str
     */
    @Override
    public String toString() {
        return sort + sourceOven;
    }
}
