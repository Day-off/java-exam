package ee.taltech.iti0202.mysticorbs.factory;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.oven.MagicOven;
import ee.taltech.iti0202.mysticorbs.oven.Oven;
import ee.taltech.iti0202.mysticorbs.oven.SpaceOven;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class OrbFactory {


    private List<Oven> ovens = new ArrayList<>();
    private final ResourceStorage resource;
    private final List<Orb> orbsAll = new ArrayList<>();
    private final List<Oven> brokenOven = new ArrayList<>();

    private static final int TWENTY_FIVE = 25;


    /***
     * constructor
     */
    public OrbFactory(ResourceStorage resourceStorage) {
        resource = resourceStorage;
    }

    /***
     * add ovens
     */
    public void addOven(Oven oven) {
        if (!ovens.contains(oven) && this.resource == oven.getResourceStorage()) {
            ovens.add(oven);
        }
    }

    /***
     * getter
     */
    public List<Oven> getOvens() {
        return ovens;
    }

    /***
     * getter
     */
    public List<Orb> getAndClearProducedOrbsList() {
        List<Orb> res = List.copyOf(orbsAll);
        orbsAll.clear();
        return res;
    }

    /***
     * check
     */
    public void checkAndFix(Oven ov) {
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

    /***
     * produce orbs one time
     */
    public int produceOrbs() {
        for (Oven ov : ovens) {
            checkAndFix(ov);
        }
        return orbsAll.size();
    }

    /***
     * produce orbs
     */
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

    /***
     * getter
     */
    public void getRidOfOvensThatCannotBeFixed() {
        brokenOven.clear();
    }

    /***
     * broken ovens
     */
    public void findBrokenOvens() {
        List<Oven> copy = List.copyOf(ovens);
        for (Oven ov : copy) {
            if (ov.getClass() == MagicOven.class) {
                if (((MagicOven) ov).getTimesFixed() == 10 && ov.isBroken()) {
                    ovens.remove(ov);
                    brokenOven.add(ov);
                }
            } else if (ov.getClass() == SpaceOven.class) {
                if (((SpaceOven) ov).getTimesFixed() == TWENTY_FIVE && ov.isBroken()) {
                    ovens.remove(ov);
                    brokenOven.add(ov);
                }
            } else if (ov.isBroken()) {
                ovens.remove(ov);
                brokenOven.add(ov);
            }
        }
    }

    /***
     * getter
     */
    public List<Oven> getOvensThatCannotBeFixed() {
        findBrokenOvens();
        return brokenOven;
    }

    /***
     * sort
     */
    public void optimizeOvensOrder() {
        ovens = ovens.stream().sorted(Oven::compareTo).collect(Collectors.toList());
        ovens = ovens.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
    }
}
