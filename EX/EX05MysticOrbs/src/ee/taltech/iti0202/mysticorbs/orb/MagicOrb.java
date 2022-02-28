package ee.taltech.iti0202.mysticorbs.orb;

import java.util.Locale;

public class MagicOrb extends Orb{

    public MagicOrb(String creator) {
        super(creator);
    }

    @Override
    public void charge(String resource, int amount) {
        String dust = "dust";
        if (!resource.trim().isEmpty() && !resource.toLowerCase(Locale.ROOT).equals(dust)) {
            charge += resource.length() * amount * 2;
        }
    }

    @Override
    public String toString() {
        return "MagicOrb by " + sourceOven;
    }

}
