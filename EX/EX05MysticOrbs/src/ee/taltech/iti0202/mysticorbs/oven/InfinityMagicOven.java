package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.orb.MagicOrb;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class InfinityMagicOven extends MagicOven {
    public InfinityMagicOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
    }

    @Override
    public boolean isBroken() {
        return false;
    }

    @Override
    public Optional<Orb> craftOrb() {
        resource1 = "gold";
        resource2 = "dust";
        res1amount = 1;
        res2amount = 3;
        if (orbs.size() == 0 && sourceStorage.hasEnoughResource(resource1, res1amount)
                && sourceStorage.hasEnoughResource(resource2, res2amount)) {
            return getOrb();
        } else if ((orbs.size() + 1) % 2 == 0) {
            if (!broken && sourceStorage.hasEnoughResource(resource1, res1amount)
                    && sourceStorage.hasEnoughResource(resource2, res2amount)) {
                MagicOrb orb = new MagicOrb(getName());
                orb.sort = "MagicOrb by ";
                orb.charge(resource1, res1amount);
                orb.charge(resource2, res2amount);
                orbs.add(orb);
                sourceStorage.takeResource(resource1, res1amount);
                sourceStorage.takeResource(resource2, res2amount);
                return Optional.of(orb);
            }
            return Optional.empty();
        } else if (sourceStorage.hasEnoughResource(resource1, res1amount)
                && sourceStorage.hasEnoughResource(resource2, res2amount) && !broken) {
            return getOrb();
        } else {
            return Optional.empty();
        }
    }

    private Optional<Orb> getOrb() {
        Orb orb = new Orb(getName());
        orb.sort = "Orb by ";
        orb.charge(resource1, res1amount);
        orb.charge(resource2, res2amount);
        orbs.add(orb);
        sourceStorage.takeResource(resource1, res1amount);
        sourceStorage.takeResource(resource2, res2amount);
        return Optional.of(orb);

    }
}
