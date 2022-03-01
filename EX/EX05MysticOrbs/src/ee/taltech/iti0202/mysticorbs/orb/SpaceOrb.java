package ee.taltech.iti0202.mysticorbs.orb;

public class SpaceOrb extends Orb {

    /***
     * constructor
     */
    public SpaceOrb(String creator) {
        super(creator);
        charge = 100;
        sort = "SpaceOrb by ";

    }

    @Override
    public void charge(String resource, int amount) {
    }

    @Override
    public String toString() {
        return sort + sourceOven;
    }

    /***
     * stole energy
     */
    public boolean absorb(Orb orb) {
        if (orb.getEnergy() < this.getEnergy()) {
            this.charge += orb.getEnergy();
            orb.charge = 0;
            return true;
        }
        return false;
    }
}
