package ee.taltech.iti0202.mysticorbs.factory;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.oven.MagicOven;
import ee.taltech.iti0202.mysticorbs.oven.Oven;
import ee.taltech.iti0202.mysticorbs.oven.SpaceOven;
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

    /***
     * add ovens
     * @param oven
     */
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

    public void checkAndFix(Oven ov){
        if (ov.isBroken()) {
            if (ov.getClass() == MagicOven.class) {
                try {
                    ((MagicOven) ov).fix();
                } catch (CannotFixException ignored) {

                }
            } else {
                if (ov.getClass() == SpaceOven.class) {
                    try {
                        ((SpaceOven) ov).fix();
                    } catch (CannotFixException ignored) {

                    }
                }
            }
        }
        if (ov.craftOrb().isPresent()) {
            orbsAll.add(ov.getOrbs().get(ov.getOrbs().size() - 1));
        }
    }

    public int produceOrbs() {
        for (Oven ov : ovens) {
            checkAndFix(ov);
        }
        return orbsAll.size();
    }

    public int produceOrbs(int cycles) {
        int count = 0;
        while (count != cycles) {
            for (Oven ov : ovens) {
                checkAndFix(ov);
            }
            count += 1;
        }
        return orbsAll.size();
    }

    public void getRidOfOvensThatCannotBeFixed() {
        brokenOven.clear();
    }

    public void findBrokenOvens(){
        List<Oven> copy = List.copyOf(ovens);
        for (Oven ov: copy){
            if (ov.getClass() == MagicOven.class) {
                if (((MagicOven) ov).getTimesFixed() == 10 && ov.isBroken()) {
                    ovens.remove(ov);
                    brokenOven.add(ov);
                }
            }else if (ov.getClass() == SpaceOven.class) {
                if (((SpaceOven) ov).getTimesFixed() == 25 && ov.isBroken()) {
                    ovens.remove(ov);
                    brokenOven.add(ov);
                }
            }else if (ov.isBroken()) {
                ovens.remove(ov);
                brokenOven.add(ov);
            }
        }
    }

    public List<Oven> getOvensThatCannotBeFixed() {
        findBrokenOvens();
        return brokenOven;
    }

    public void optimizeOvensOrder() {

    }
}
