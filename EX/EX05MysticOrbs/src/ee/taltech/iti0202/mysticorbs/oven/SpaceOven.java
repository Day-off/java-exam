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
            amount = 20;
            return super.isBroken();
        } else {
            return false;
        }
    }

    @Override
    public Optional<Orb> craftOrb() {
        if (sourceStorage.hasEnoughResource("meteorite stone", 1)
                && sourceStorage.hasEnoughResource("star fragment", 15)) {
            Orb orb = new SpaceOrb(getName());
            orbs.add(orb);
            sourceStorage.takeResource("meteorite stone", 1);
            sourceStorage.takeResource("star fragment", 15);
            return Optional.of(orb);
        } else {
            return super.craftOrb();
        }
    }

    @Override
    public void fix() throws CannotFixException {
        if (!isBroken()) {
            throw new CannotFixException(this, CannotFixException.Reason.IS_NOT_BROKEN);
        } else if (fixTime >= 5) {
            amount = orbs.size();
            throw new CannotFixException(this, CannotFixException.Reason.IS_NOT_BROKEN);
        } else if (!sourceStorage.hasEnoughResource("liquid silver", 40 * (fixTime + 1))) {
            if (!sourceStorage.hasEnoughResource("star essence", 10 * (fixTime + 1))) {
                throw new CannotFixException(this, CannotFixException.Reason.NOT_ENOUGH_RESOURCES);
            } else {
                fixTime += 1;
                sourceStorage.takeResource("star essence", 10 * (fixTime + 1));
                orbs.clear();
            }
        } else {
            fixTime += 1;
            sourceStorage.takeResource("liquid silver", 40 * (fixTime + 1));
            orbs.clear();
        }
    }

    @Override
    public int getTimesFixed() {
        return fixTime;
    }

}
