package listeners.Event;

import unhappyEC.Entity;

public class AfterDamage extends BeforeDamage {
    private final int remainingHealth;

    public AfterDamage(Entity source, Entity target, int value, int remainingHealth) {
        super(source, target, value);
        this.remainingHealth = remainingHealth;
    }

    public int getRemainingHealth() {
        return remainingHealth;
    }
}
