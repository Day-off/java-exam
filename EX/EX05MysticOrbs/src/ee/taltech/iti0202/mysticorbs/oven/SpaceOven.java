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
        amount = 25;
    }

    @Override
    public boolean isBroken() {
        if (fixTime <= 5) {
            return super.isBroken();
        } else {
            broken = false;
            return false;
        }
    }

    @Override
    public Optional<Orb> craftOrb() {
        if (sourceStorage.hasEnoughResource("meteorite stone", 1)
                && sourceStorage.hasEnoughResource("star fragment", 15) && !isBroken()) {
            SpaceOrb orb = new SpaceOrb(getName());
            orbs.add(orb);
            sourceStorage.takeResource("meteorite stone", 1);
            sourceStorage.takeResource("star fragment", 15);
            return Optional.of(orb);
        } else if (sourceStorage.hasEnoughResource("pearl", 1)
                && sourceStorage.hasEnoughResource("silver", 1)) {
            Orb orb = new Orb(getName());
            sourceStorage.takeResource("pearl", 1);
            sourceStorage.takeResource("silver", 1);
            orb.charge("pearl", 1);
            orb.charge("silver", 1);
            orbs.add(orb);
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
                amount += 25;
            }
        } else {
            broken = false;
            fixTime += 1;
            sourceStorage.takeResource("liquid silver", 40);
            amount += 25;
        }
    }

    @Override
    public int getTimesFixed() {
        return fixTime;
    }

}
