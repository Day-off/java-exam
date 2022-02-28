package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.MagicOrb;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;


public class MagicOven extends Oven implements Fixable {

    private int fixTime = 0;

    public MagicOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
    }

    @Override
    public boolean isBroken() {
        amount = 5;
        return super.isBroken();
    }

    @Override
    public Optional<Orb> craftOrb() {
        resource1 = "gold";
        resource2 = "dust";
        res1amount = 1;
        res2amount = 3;
        if (orbs.size() == 0 && sourceStorage.hasEnoughResource(resource1, res1amount)
                && sourceStorage.hasEnoughResource(resource2, res2amount)) {
            Orb orb = new Orb(getName());
            orb.charge(resource1, res1amount);
            orb.charge(resource2, res2amount);
            orbs.add(orb);
            sourceStorage.takeResource(resource1, res1amount);
            sourceStorage.takeResource(resource2, res2amount);
            return Optional.of(orb);
        } else if ((orbs.size() + 1) % 2 == 0) {
            if (!broken && sourceStorage.hasEnoughResource(resource1, res1amount)
                    && sourceStorage.hasEnoughResource(resource2, res2amount)) {
                MagicOrb orb = new MagicOrb(getName());
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
            Orb orb = new Orb(getName());
            orb.charge(resource1, res1amount);
            orb.charge(resource2, res2amount);
            orbs.add(orb);
            sourceStorage.takeResource(resource1, res1amount);
            sourceStorage.takeResource(resource2, res2amount);
            return Optional.of(orb);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void fix() throws CannotFixException {
        if (!isBroken()) {
            throw new CannotFixException(this, CannotFixException.Reason.IS_NOT_BROKEN);
        } else if (fixTime >= 10) {
            throw new CannotFixException(this, CannotFixException.Reason.FIXED_MAXIMUM_TIMES);
        } else if (!sourceStorage.hasEnoughResource("clay", 25 * (fixTime + 1)) ||
                !sourceStorage.hasEnoughResource("freezing powder", 100 * (fixTime + 1))) {
            throw new CannotFixException(this, CannotFixException.Reason.NOT_ENOUGH_RESOURCES);
        } else {
            fixTime += 1;
            sourceStorage.takeResource("clay", 25 * (fixTime + 1));
            sourceStorage.takeResource("freezing powder", 100 * (fixTime + 1));
            orbs.clear();
        }
    }

    @Override
    public int getTimesFixed() {
        return fixTime;
    }
}
