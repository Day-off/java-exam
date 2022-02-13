package ee.taltech.iti0202.lotr;

import java.util.Objects;

public class Person {
    private final String Race;
    private final String Name;
    private String res;
    private Ring Ring;

    public Person(String race, String name, Ring ring) {
        Race = race;
        Name = name;
        Ring = ring;
    }

    public Person(String race, String name) {
        Race = race;
        Name = name;
    }

    public void setRing(Ring ring) {
        Ring = ring;
    }

    public void check() {
        if (Objects.equals(Name, "Sauron") && Ring.getType() == ee.taltech.iti0202.lotr.Ring.Type.THE_ONE
                && Ring.getMaterial() == ee.taltech.iti0202.lotr.Ring.Material.GOLD) {
            res = "Affirmative";
        } else if (Objects.equals(Name, "Sauron") && Ring.getType() == ee.taltech.iti0202.lotr.Ring.Type.THE_ONE
                && Ring.getMaterial() != ee.taltech.iti0202.lotr.Ring.Material.GOLD) {
            res = "No, the ring is fake!";
        } else if (!Objects.equals(Name, "Sauron") && Ring.getType() == ee.taltech.iti0202.lotr.Ring.Type.THE_ONE
                && Ring.getMaterial() == ee.taltech.iti0202.lotr.Ring.Material.GOLD) {
            res = "No, he just stole the ring.";
        } else if (Objects.equals(Name, "Sauron") && (Ring.getType() != ee.taltech.iti0202.lotr.Ring.Type.THE_ONE
                || Ring == null)) {
            res = "No, but he's claiming to be.";
        } else {
            res = "No";
        }
    }


    public String isSauron() {
        check();
        return res;
    }

    public String getRace() {
        return Race;
    }

    public String getName() {
        return Name;
    }

    public ee.taltech.iti0202.lotr.Ring getRing() {
        return Ring;
    }
}
