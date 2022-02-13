package ee.taltech.iti0202.lotr;

import java.util.Objects;

public class Person {
    private final String pRace;
    private final String pName;
    private String res;
    private Ring pRing;

    public Person(String race, String name, Ring ring) {
        pRace = race;
        pName = name;
        pRing = ring;
    }

    public Person(String race, String name) {
        pRace = race;
        pName = name;
    }

    public void setRing(Ring ring) {
        pRing = ring;
    }

    public void check() {
        if (Objects.equals(pName, "Sauron") && pRing.getType() == ee.taltech.iti0202.lotr.Ring.Type.THE_ONE
                && pRing.getMaterial() == ee.taltech.iti0202.lotr.Ring.Material.GOLD) {
            res = "Affirmative";
        } else if (Objects.equals(pName, "Sauron") && pRing.getType() == ee.taltech.iti0202.lotr.Ring.Type.THE_ONE
                && pRing.getMaterial() != ee.taltech.iti0202.lotr.Ring.Material.GOLD) {
            res = "No, the ring is fake!";
        } else if (!Objects.equals(pName, "Sauron") && pRing.getType() == ee.taltech.iti0202.lotr.Ring.Type.THE_ONE
                && pRing.getMaterial() == ee.taltech.iti0202.lotr.Ring.Material.GOLD) {
            res = "No, he just stole the ring";
        } else if (Objects.equals(pName, "Sauron") && pRing == null) {
            res = "No, but he's claiming to be";
        } else if (Objects.equals(pName, "Sauron") && pRing.getType() != ee.taltech.iti0202.lotr.Ring.Type.THE_ONE) {
            res = "No, but he's claiming to be";
        } else {
            res = "No";
        }
    }


    public String isSauron() {
        check();
        return res;
    }

    public String getRace() {
        return pRace;
    }

    public String getName() {
        return pName;
    }

    public ee.taltech.iti0202.lotr.Ring getRing() {
        return pRing;
    }
}
