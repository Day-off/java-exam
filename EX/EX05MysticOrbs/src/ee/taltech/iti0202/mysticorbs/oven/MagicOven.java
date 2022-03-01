package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.MagicOrb;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;


public class MagicOven extends Oven implements Fixable {

    private int fixTime = 0;
    private static final int twentyFive = 25;


    /***
     * constructor
     */
    public MagicOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
        amount = 5;

    }

    @Override
    public boolean isBroken() {
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

    @Override
    public void fix() throws CannotFixException {
        if (!isBroken()) {
            throw new CannotFixException(this, CannotFixException.Reason.IS_NOT_BROKEN);
        } else if (fixTime >= 10) {
            throw new CannotFixException(this, CannotFixException.Reason.FIXED_MAXIMUM_TIMES);
        } else if (!sourceStorage.hasEnoughResource("clay", twentyFive * (fixTime + 1))
                || !sourceStorage.hasEnoughResource("freezing powder", 100 * (fixTime + 1))) {
            throw new CannotFixException(this, CannotFixException.Reason.NOT_ENOUGH_RESOURCES);
        } else {
            fixTime += 1;
            broken = false;
            sourceStorage.takeResource("clay", twentyFive * (fixTime));
            sourceStorage.takeResource("freezing powder", 100 * (fixTime));
            amount += 5;
        }
    }

    @Override
    public int getTimesFixed() {
        return fixTime;
    }


}
