package ee.taltech.iti0202.mysticorbs.factory;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.oven.MagicOven;
import ee.taltech.iti0202.mysticorbs.oven.Oven;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.ArrayList;
import java.util.List;

public class OrbFactory {


    private List<Oven> ovens = new ArrayList<>();
    private ResourceStorage resource;
    private List<Orb> orbsAll = new ArrayList<>();
    private List<Oven> brokenOven = new ArrayList<>();

    public OrbFactory(ResourceStorage resourceStorage) {
        resource = resourceStorage;
    }

    public void addOven(Oven oven) {
        if (!ovens.contains(oven) && this.resource == oven.getResourceStorage()) {
            ovens.add(oven);
        }
    }

    public List<Oven> getOvens() {
        return ovens;
    }

    public List<Orb> getAndClearProducedOrbsList() {
        List<Orb> res = List.copyOf(orbsAll);
        orbsAll.clear();
        return res;
    }

    public int produceOrbs() throws CannotFixException {
        for (Oven ov : ovens) {
            if (ov.isBroken() && ov.getClass() == MagicOven.class) {
                ((MagicOven) ov).fix();
                getRidOfOvensThatCannotBeFixed();
            }
            ov.craftOrb();
            orbsAll.add(ov.getOrbs().get(ov.getOrbs().size() - 1));
        }
        return orbsAll.size();
    }

    public int produceOrbs(int cycles) {
        int count = 0;
        while (count != cycles) {
            for (Oven ov : ovens) {
                ov.craftOrb();
                orbsAll.add(ov.getOrbs().get(ov.getOrbs().size() - 1));
            }
            count += 1;
        }
        return orbsAll.size();
    }

    public void getRidOfOvensThatCannotBeFixed() {
        for (Oven ov : ovens) {
            if (ov.getClass() == MagicOven.class) {
                if (((MagicOven) ov).getTimesFixed() == 10 && ov.isBroken()) {
                    ovens.remove(ov);
                    brokenOven.add(ov);
                }
            }
        }
    }

    public List<Oven> getOvensThatCannotBeFixed() {
        getRidOfOvensThatCannotBeFixed();
        return brokenOven;
    }

    public void optimizeOvensOrder() {

    }
}
