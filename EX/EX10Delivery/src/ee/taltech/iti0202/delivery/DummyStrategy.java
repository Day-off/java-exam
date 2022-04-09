package ee.taltech.iti0202.delivery;

import java.util.List;

public class DummyStrategy implements Strategy {
    private final List<Action> actions;
    private int i = -1;

    public DummyStrategy(List<Action> actions) {
        this.actions = actions;
    }

    @Override
    public Action getAction() {
        i += 1;
        return actions.get(i);
    }
}
