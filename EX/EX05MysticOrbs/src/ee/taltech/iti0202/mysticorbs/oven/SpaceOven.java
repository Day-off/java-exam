package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.orb.SpaceOrb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class SpaceOven extends Oven implements Fixable {

    private int fixTime = 0;

    public SpaceOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
    }

    @Override
    public boolean isBroken() {
        if (fixTime < 5) {
            amount = 25;
            return super.isBroken();
        } else {
            broken = false;
            return false;
        }
    }

    @Override
    public Optional<Orb> craftOrb() {
        resource1 = "pearl";
        resource2 = "silver";
        res1amount = 1;
        res2amount = 1;
        if (sourceStorage.hasEnoughResource("meteorite stone", 1)
                && sourceStorage.hasEnoughResource("star fragment", 15) && !broken) {
            SpaceOrb orb = new SpaceOrb(getName());
            orbs.add(orb);
            sourceStorage.takeResource("meteorite stone", 1);
            sourceStorage.takeResource("star fragment", 15);
            return Optional.of(orb);
        } else if (sourceStorage.hasEnoughResource(resource1, res1amount)
                && sourceStorage.hasEnoughResource(resource2, res2amount)) {
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
        } else if (!sourceStorage.hasEnoughResource("liquid silver", 40)) {
            if (!sourceStorage.hasEnoughResource("star essence", 10)) {
                throw new CannotFixException(this, CannotFixException.Reason.NOT_ENOUGH_RESOURCES);
            } else {
                broken = false;
                fixTime += 1;
                sourceStorage.takeResource("star essence", 10);
                orbs.clear();
            }
        } else {
            broken = false;
            fixTime += 1;
            sourceStorage.takeResource("liquid silver", 40);
            orbs.clear();
        }
    }

    @Override
    public int getTimesFixed() {
        return fixTime;
    }

}
