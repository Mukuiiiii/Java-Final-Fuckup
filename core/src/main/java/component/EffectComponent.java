package component;

import unhappyEC.Component;
import unhappyEC.Entity;
import java.util.function.Predicate;

public class EffectComponent extends Component {
    public String triggerType;
    public Predicate<Entity> condition; //I haven't write it.
    public Runnable action;

    public EffectComponent() {
        this(null, null, null);
    }

    public EffectComponent(String triggerType, Predicate<Entity> condition, Runnable action) {
        this.triggerType = triggerType;
        this.condition = condition;
        this.action = action;
    }
}
