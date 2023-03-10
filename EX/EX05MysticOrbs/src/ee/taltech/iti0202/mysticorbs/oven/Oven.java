package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Oven implements Comparable<Oven> {

    protected String ovenName;
    protected String resource1 = "pearl";
    protected String resource2 = "silver";
    protected int res1amount = 1;
    protected int res2amount = 1;
    protected ResourceStorage sourceStorage;
    protected List<Orb> orbs = new ArrayList<>();
    private static final int FIFTEEN = 15;
    protected int amount = FIFTEEN;
    protected boolean broken = false;

    /***
     * constructor
     */
    public Oven(String name, ResourceStorage resourceStorage) {
        ovenName = name;
        sourceStorage = resourceStorage;
    }

    public String getName() {
        return ovenName;
    }

    public ResourceStorage getResourceStorage() {
        return sourceStorage;
    }

    public List<Orb> getOrbs() {
        return orbs;
    }

    public int getCreatedOrbsAmount() {
        return orbs.size();
    }

    /***
     * check if broken
     */
    public boolean isBroken() {
        broken = orbs.size() >= amount;
        return broken;
    }

    /***
     * craft
     */
    public Optional<Orb> craftOrb() {
        String resource1 = "pearl";
        String resource2 = "silver";
        if (!isBroken() && sourceStorage.hasEnoughResource(resource1, res1amount)
                && sourceStorage.hasEnoughResource(resource2, res2amount)) {
            Orb orb = new Orb(getName());
            orb.charge(resource1, res1amount);
            orb.charge(resource2, res2amount);
            orbs.add(orb);
            sourceStorage.takeResource(resource1, res1amount);
            sourceStorage.takeResource(resource2, res2amount);
            return Optional.of(orb);
        }
        return Optional.empty();
    }

    @Override
    public int compareTo(Oven o) {
        if (!this.isBroken() && o.isBroken()) {
            return 1;
        } else if (this.isBroken() && !o.isBroken()) {
            return -1;
        } else {
            if (this.getClass() == SpaceOven.class && o.getClass() != SpaceOven.class) {
                return 1;
            } else if (this.getClass() != SpaceOven.class && o.getClass() == SpaceOven.class) {
                return -1;
            } else {
                if (this instanceof MagicOven && !(o instanceof MagicOven)) {
                    return 1;
                } else if (!(this instanceof MagicOven) && o instanceof MagicOven) {
                    return -1;
                } else {
                    if (this instanceof MagicOven) {
                        if (this.orbs.size() % 2 != 0 && o.orbs.size() % 2 == 0) {
                            return 1;
                        } else if (this.orbs.size() % 2 == 0 && o.orbs.size() % 2 != 0) {
                            return -1;
                        } else {
                            if (this instanceof InfinityMagicOven && !(o instanceof InfinityMagicOven)
                                    && this.orbs.size() == o.orbs.size()) {
                                return 1;
                            } else if (o instanceof InfinityMagicOven && !(this instanceof InfinityMagicOven)
                                    && this.orbs.size() == o.orbs.size()) {
                                return -1;
                            } else {
                                if (this.orbs.size() < o.orbs.size()) {
                                    return 1;
                                } else if (this.orbs.size() > o.orbs.size()) {
                                    return -1;
                                } else {
                                    if (this.getName().compareTo(o.getName()) == 0) {
                                        return 0;
                                    } else {
                                        return this.getName().compareTo(o.getName());
                                    }
                                }
                            }
                        }
                    } else {
                        if (this.orbs.size() < o.orbs.size()) {
                            return 1;
                        } else if (this.orbs.size() > o.orbs.size()) {
                            return -1;
                        } else {
                            if (this.getName().compareTo(o.getName()) == 0) {
                                return 0;
                            } else {
                                return this.getName().compareTo(o.getName());
                            }
                        }
                    }
                }
            }
        }
    }
}
