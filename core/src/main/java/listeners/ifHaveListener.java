package listeners;

import java.util.function.Predicate;
import listeners.Event.Drawcard;
import unhappyEC.ECManager;
import unhappyEC.Entity;

public class ifHaveListener extends Listener<Drawcard> {
    private final ECManager ecManager;
    private final Predicate<Entity> condition;
    private final Runnable action;

    public ifHaveListener(ECManager ecManager, Predicate<Entity> condition, Runnable action) {
        super(Drawcard.class);
        this.ecManager = ecManager;
        this.condition = condition;
        this.action = action;
    }

    @Override
    public void onEvent(Drawcard event) {
        boolean match = ecManager.getEntities().stream()
            .anyMatch(condition);

        if (match) {
            action.run();
        }
    }
}
