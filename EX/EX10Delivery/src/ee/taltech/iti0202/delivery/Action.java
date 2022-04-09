package ee.taltech.iti0202.delivery;

import java.util.ArrayList;
import java.util.List;

public class Action {

    private final Location goTo;
    private final List<String> take = new ArrayList<>();
    private final List<String> put = new ArrayList<>();

    public Action(Location goTo) {
        this.goTo = goTo;
    }

    public void addTake(String pack) {
        take.add(pack);
    }

    public void addDeposit(String pack) {
        put.add(pack);
    }

    public Location getGoTo() {
        return goTo;
    }

    public List<String> getDeposit() {
        return put;
    }

    public List<String> getTake() {
        return take;
    }
}
