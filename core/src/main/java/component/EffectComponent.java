package component;

import unhappyEC.Component;
import unhappyEC.Entity;
import

public class EffectComponent extends Component {
    public String triggerType;
    public Predicate<Entity> condition; //I haven't write it.
    public Runnable action;
}
