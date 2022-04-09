package ee.taltech.iti0202.delivery;

import java.util.ArrayList;
import java.util.List;

public class DummyStrategy implements Strategy {
    private final ArrayList<Action> actions;

    public DummyStrategy(List<Action> actions) {
        this.actions = new ArrayList<>(actions);
    }

    @Override
    public Action getAction() {
        return actions.remove(0);
    }
}
