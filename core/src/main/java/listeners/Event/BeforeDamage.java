package listeners.Event;

import listeners.Events;
import unhappyEC.Entity;

public class BeforeDamage extends Events {
    private final Entity source;
    private final Entity target;
    private final int value;

    public BeforeDamage(Entity source, Entity target, int value) {
        this.source = source;
        this.target = target;
        this.value = value;
    }

    public Entity getSource() {
        return source;
    }

    public Entity getTarget() {
        return target;
    }

    public int getValue() {
        return value;
    }
}
