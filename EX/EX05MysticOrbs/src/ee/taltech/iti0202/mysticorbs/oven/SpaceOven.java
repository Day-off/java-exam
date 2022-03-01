package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.orb.SpaceOrb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class SpaceOven extends Oven implements Fixable {

    private int fixTime = 0;
    private static final int TWENTY_FIVE = 25;
    private static final int FORTY = 40;
    private static final int FIFTEEN = 15;


    /***
     * constructor
     */
    public SpaceOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
        amount = TWENTY_FIVE;
    }

    @Override
    public boolean isBroken() {
        if (fixTime < 5) {
            return super.isBroken();
        } else {
            broken = false;
            return false;
        }
    }

    @Override
    public Optional<Orb> craftOrb() {
        if (sourceStorage.hasEnoughResource("meteorite stone", 1)
                && sourceStorage.hasEnoughResource("star fragment", FIFTEEN) && !isBroken()) {
            SpaceOrb orb = new SpaceOrb(getName());
            orbs.add(orb);
            sourceStorage.takeResource("meteorite stone", 1);
            sourceStorage.takeResource("star fragment", FIFTEEN);
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
        } else if (!sourceStorage.hasEnoughResource("liquid silver", FORTY)) {
            if (!sourceStorage.hasEnoughResource("star essence", 10)) {
                throw new CannotFixException(this, CannotFixException.Reason.NOT_ENOUGH_RESOURCES);
            } else {
                broken = false;
                fixTime += 1;
                sourceStorage.takeResource("star essence", 10);
                amount += TWENTY_FIVE;
            }
        } else {
            broken = false;
            fixTime += 1;
            sourceStorage.takeResource("liquid silver", FORTY);
            amount += TWENTY_FIVE;
        }
    }

    @Override
    public int getTimesFixed() {
        return fixTime;
    }

}
